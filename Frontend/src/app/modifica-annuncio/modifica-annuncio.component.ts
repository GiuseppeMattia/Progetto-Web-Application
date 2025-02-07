import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {UserModel} from '../modelli/userModel';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {AuthService} from '../services/auth.service';
import {AnnuncioService} from '../services/annuncio.service';
import {Annuncio} from '../modelli/annuncio.model';

@Component({
  selector: 'app-modifica-annuncio',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './modifica-annuncio.component.html',
  styleUrl: './modifica-annuncio.component.css'
})
export class ModificaAnnuncioComponent implements OnInit{
  protected modifyForm: FormGroup;
  private user: UserModel | null;
  protected annuncio: Annuncio | null;
  private id: string | null;

  private array: Uint8Array;

  constructor(private router: Router, private fb: FormBuilder, private authService: AuthService, private annuncioService: AnnuncioService, private route: ActivatedRoute) {
    this.modifyForm = this.fb.group({
      titolo: ["", Validators.required],
      prezzo: [null, Validators.required],
      marca: ["", Validators.required],
      modello: ["", Validators.required],
      descrizione: ["", Validators.required],
      scontaprezzo: [null, Validators.required]
    })

    this.array = new Uint8Array;
    this.user = this.authService.currentUserValue;
    this.id = "";
    this.annuncio = null;

  }



  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });

    this.annuncioService.getAnnuncio(Number(this.id)).subscribe(annuncio => {
      this.annuncio = annuncio;
      this.modifyForm.patchValue({
        titolo: this.annuncio.titolo,
        prezzo: this.annuncio.prezzo,
        marca: this.annuncio.marca,
        modello: this.annuncio.modello,
        descrizione: this.annuncio.descrizione,
        scontaprezzo: this.annuncio.prezzoScontato
      });

      if (this.user?.username !== this.annuncio?.venditore.username && !this.user?.amministratore) {
        this.router.navigate(['/home']);
      }
    });

  }




  modificaAnnuncio(){
    if (!this.user || !this.annuncio) {
      return;
    }

    // Campi obbligatori
    if (
      !this.modifyForm.get('titolo')?.value ||
      !this.modifyForm.get('prezzo')?.value ||
      !this.modifyForm.get('marca')?.value ||
      !this.modifyForm.get('modello')?.value
    ) {
      alert("Per favore, completa i campi richiesti");
      console.log(this.modifyForm.get('titolo')?.value)
      console.log(this.modifyForm.get('prezzo')?.value)
      console.log(this.modifyForm.get('marca')?.value)
      console.log(this.modifyForm.get('modello')?.value)
      return;
    }

    const binaryString = Array.from(this.array).map(byte => String.fromCharCode(byte)).join('');


    let annuncio = new Annuncio(
      Number(this.id),
      this.annuncio?.categoria,
      this.modifyForm.get("marca")?.value,
      this.modifyForm.get("modello")?.value,
      this.modifyForm.get("prezzo")?.value,
      this.modifyForm.get("descrizione")?.value,
      this.modifyForm.get("titolo")?.value,
      this.modifyForm.get("scontaprezzo")?.value,
      this.user,
      btoa(binaryString),
      []
    )

    this.annuncioService.aggiornaAnnuncio(annuncio).subscribe(
      response => {
        this.router.navigate([`/annuncio/${this.id}`]);
      },
      error => {
        console.error('Errore nella creazione dell\'annuncio', error);
      }
    );

  }

  goBack() {
    this.router.navigate([`/annuncio/${this.id}`]);
  }



}
