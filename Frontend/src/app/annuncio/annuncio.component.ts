import { Component, type OnInit } from "@angular/core";
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
import {Asta} from '../modelli/asta';  // Aggiunto FormBuilder e FormGroup

@Component({
  selector: "app-annuncio",
  templateUrl: "./annuncio.component.html",
  styleUrls: ["./annuncio.component.css"],
  standalone: true,
  imports: [CommonModule, NgIf, HttpClientModule, ReactiveFormsModule, RouterLink],
  providers: [AnnuncioService, RecensioneService, AuthService]
})
export class AnnuncioComponent implements OnInit {
  annuncio: Annuncio | null = null;
  errorMessage = "";
  reviewForm: FormGroup;  // Aggiunta la dichiarazione del FormGroup per la recensione
  private asta: Asta | null;

  protected user: UserModel | null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private annuncioService: AnnuncioService,
    private recensioneService: RecensioneService,
    private authService: AuthService,
    private sanitizer: DomSanitizer,
    private fb: FormBuilder,  // Aggiunto il FormBuilder per creare il form

  ) {
    // Inizializzazione del form di recensione
    this.reviewForm = this.fb.group({
      text: ['', Validators.required]
    });

    this.user = this.authService.currentUserValue
    this.asta = null;
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get("id");
    if (id) {
      // console.log("ID ricevuto", id);
      this.loadAnnuncio(Number(id));
    }
  }

  loadAnnuncio(id: number) {
    this.annuncioService.getAnnuncio(id).subscribe(
      (annuncio) => {
        this.annuncio = annuncio;  // Assegna l'annuncio
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
}
