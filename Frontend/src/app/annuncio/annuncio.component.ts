import {Component, OnDestroy, type OnInit} from "@angular/core";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import { CommonModule, NgIf } from "@angular/common";
import { Annuncio } from "../modelli/annuncio.model";
import { AnnuncioService } from "../services/annuncio.service";
import { HttpClientModule } from '@angular/common/http';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {RecensioneService} from '../services/recensione.service';
import {Recensione} from '../modelli/Recensione';
import {AuthService} from '../services/auth.service';
import {UserModel} from '../modelli/userModel';
import {Asta} from '../modelli/asta';
import {AstaService} from '../services/asta.service';
import {interval, Subject, takeUntil} from 'rxjs';  // Aggiunto FormBuilder e FormGroup

@Component({
  selector: "app-annuncio",
  templateUrl: "./annuncio.component.html",
  styleUrls: ["./annuncio.component.css"],
  standalone: true,
  imports: [CommonModule, NgIf, HttpClientModule, ReactiveFormsModule, RouterLink],
  providers: [AnnuncioService, RecensioneService, AuthService]
})
export class AnnuncioComponent implements OnInit, OnDestroy {
  annuncio: Annuncio | null = null;
  errorMessage = "";
  reviewForm: FormGroup;  // Aggiunta la dichiarazione del FormGroup per la recensione
  priceForm: FormGroup;
  protected asta: Asta | null;
  protected tempoRimanente: String;

  protected user: UserModel | null;
  private unsubscribe$ = new Subject<void>(); // Per pulire il timer quando il componente viene distrutto


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private annuncioService: AnnuncioService,
    private recensioneService: RecensioneService,
    private authService: AuthService,
    private sanitizer: DomSanitizer,
    private fb: FormBuilder,  // Aggiunto il FormBuilder per creare il form
    private astaService: AstaService
  ) {
    // Inizializzazione del form di recensione
    this.reviewForm = this.fb.group({
      text: ['', Validators.required]
    });

    this.priceForm = this.fb.group({
      price: [null, Validators.required]
    })

    this.user = this.authService.currentUserValue
    this.asta = null;
    this.tempoRimanente = "";
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get("id");
    if (id) {
      // console.log("ID ricevuto", id);
      this.loadAnnuncio(Number(id));

        this.aggiornaTimer(); // Calcola il tempo iniziale

        // Esegui l'aggiornamento ogni secondo
        interval(1000)
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe(() => this.aggiornaTimer());
      }

  }

  loadAnnuncio(id: number) {
    this.annuncioService.getAnnuncio(id).subscribe(
      (annuncio) => {
        this.annuncio = annuncio;  // Assegna l'annuncio
        this.astaService.getAstaByAnnuncio(this.annuncio.id).subscribe(
          (asta) =>{
            this.asta = asta;
          },
          (error) =>{
            console.error("Errore nel caricamento dell'asta:", error);
            this.errorMessage = "Impossibile caricare l'asta. Si prega di riprovare più tardi.";
          }
        );
      },
      (error) => {
        console.error("Errore nel caricamento dell'annuncio:", error);
        this.errorMessage = "Impossibile caricare l'annuncio. Si prega di riprovare più tardi.";
      }
    );
  }



  getImageUrl(byte: string): SafeUrl {
    const byteCharacters = atob(byte);
    const byteArray = new Uint8Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
      byteArray[i] = byteCharacters.charCodeAt(i);
    }
    const blob = new Blob([byteArray], { type: 'image/png' });
    const url = URL.createObjectURL(blob);
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }

  contatta() {
    if (this.authService.isLoggedIn()){
      let numero = this.annuncio?.venditore.telefono;
      window.open("https://wa.me/" + numero, "_blank");
    } else {
      alert("Fai il login per contattare un venditore")
      this.router.navigate(['/login'])
    }

  }

  submitReview(): void {
    if (this.reviewForm.valid) {
      if (!this.user || !this.annuncio) {
        return;
      }

      const testo = this.reviewForm.value.text;

      let recensione = new Recensione(
        0,  // da ignorare
        testo,
        this.user,
        this.annuncio
      )

      // Chiamata al servizio per inviare la recensione
      this.recensioneService.creaRecensione(recensione).subscribe(
        (response) => {
          console.log('Recensione inviata con successo:', response);
          // Aggiungi la recensione alla lista senza ricaricare l'annuncio
          this.annuncio!.recensioni.push(recensione);
          this.reviewForm.reset();  // Resetta il form
        },
        (error) => {
          console.error('Errore nell\'invio della recensione', error);
        }
      );
    }
  }

  submitPrice(){
      if(!this.priceForm.get("price")?.value || !this.asta){
        return;
      }

      if(!this.user){
        alert("Accedi prima di poter inserire un prezzo");
        this.router.navigate(["/login"]);
        return;
      }

      if(this.priceForm.get("price")?.value > this.asta?.prezzo){
          this.asta.prezzo = Number(this.priceForm.get("price")?.value);
          this.asta.acquirente = this.user;
          this.astaService.aggiornaAsta(this.asta).subscribe(
            (response) =>{
              window.location.reload()
            },
            (error ) =>{
              alert("Impossibile aggiungere il prezzo");
              return;
        }
          );

      }
      else{
        alert("Il prezzo inserito non è valido")
      }
  }

  aggiornaTimer() {
    if(!this.asta){
      return
    }

    const adesso = new Date().getTime();
    const scadenza = new Date(this.asta?.scadenza).getTime();
    const differenza = scadenza - adesso;

    if (differenza <= 0) {
      this.asta.isTerminated = true;
      this.astaService.aggiornaAsta(this.asta).subscribe(
        (response) =>{
          window.location.reload();
        }
      )

      this.unsubscribe$.next(); // Ferma il timer
      return;
    }

    // Calcola giorni, ore, minuti e secondi rimanenti
    const giorni = Math.floor(differenza / (1000 * 60 * 60 * 24));
    const ore = Math.floor((differenza % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    const minuti = Math.floor((differenza % (1000 * 60 * 60)) / (1000 * 60));
    const secondi = Math.floor((differenza % (1000 * 60)) / 1000);

    this.tempoRimanente = `${giorni}g ${ore}h ${minuti}m ${secondi}s`;
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
