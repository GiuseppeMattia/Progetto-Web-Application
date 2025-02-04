import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {NgForOf} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {Annuncio} from '../modelli/annuncio.model';
import {Categoria} from '../modelli/categoria';
import {CategoriaService} from '../services/categoria.service';
import {AuthService} from '../services/auth.service';
import {UserModel} from '../modelli/userModel';
import {AnnuncioService} from '../services/annuncio.service';
import {Recensione} from '../modelli/Recensione';

@Component({
  selector: 'app-crea',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    RouterLink,
    ReactiveFormsModule
  ],
  templateUrl: './crea.component.html',
  styleUrl: './crea.component.css'
})
export class CreaComponent implements OnInit{
  protected createForm: FormGroup;
  private user: UserModel | null;

  categoriaScelta: number | null =null;
  categorie:Categoria[]=[]
  private array: Uint8Array;

  constructor(private router: Router, private categoriaService: CategoriaService, private fb: FormBuilder, private authService: AuthService, private annuncioService: AnnuncioService) {
    this.createForm = this.fb.group({
      titolo: ["", Validators.required],
      prezzo: [null, Validators.required],
      marca: ["", Validators.required],
      modello: ["", Validators.required],
      immagine: [null, Validators.required],
      descrizione: ["", Validators.required],
      categoria: [null, Validators.required]
    })

    this.array = new Uint8Array;
    this.user = this.authService.currentUserValue;
  }

  // Trasforma l'immagine in un array di byte
  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();

      reader.onload = (e) => {
        const arrayBuffer = reader.result as ArrayBuffer;
        const byteArray = new Uint8Array(arrayBuffer);
        this.array = byteArray

        console.log(byteArray);
      };

      reader.readAsArrayBuffer(file);
    }
  }

  ngOnInit(): void {
    this.loadCategorie();
  }

  loadCategorie(){
    this.categoriaService.trovaTutte().subscribe(
      (categorie) => {
        this.categorie = categorie;
        console.log('Categorie caricate:', this.categorie);
      },
      (error) => {
        console.error('Errore nel caricamento delle categorie:', error);
      }
    );
  }

  selectCategoria(categoriaId: number): void {
    const categoriaSelezionata = this.categorie.find(cat => cat.id === categoriaId);

    if (categoriaSelezionata) {
      this.createForm.patchValue({ categoria: categoriaSelezionata });
    }
  }

  createAnnuncio(){
    // L'utente deve essere loggato per poter fare l'annuncio
    if (!this.user) {
      return;
    }

    // Campi obbligatori
    if (
      !this.createForm.get('titolo')?.value ||
      !this.createForm.get('prezzo')?.value ||
      !this.createForm.get('marca')?.value ||
      !this.createForm.get('modello')?.value ||
      !this.createForm.get('categoria')?.value
    ) {
      alert("Per favore, completa i campi richiesti");
      return;
    }

    const binaryString = Array.from(this.array).map(byte => String.fromCharCode(byte)).join('');


    let annuncio = new Annuncio(
      0,    // da ignorare
      this.createForm.get("categoria")?.value,
      this.createForm.get("marca")?.value,
      this.createForm.get("modello")?.value,
      this.createForm.get("prezzo")?.value,
      this.createForm.get("descrizione")?.value,
      this.createForm.get("titolo")?.value,
      null,
      this.user,
      btoa(binaryString),
      []
    )

    // console.log(this.array);
    // console.log(binaryString);  // Dovresti vedere la stringa binaria corretta
    //
    // console.log(btoa(binaryString))
    console.log('Annuncio inviato al backend:', annuncio); // Log dell'oggetto prima di inviarlo


    // Esegui la chiamata POST e gestisci la risposta
    this.annuncioService.creaAnnuncio(annuncio).subscribe(
      response => {
        console.log('Annuncio creato con successo', response);
        this.router.navigate(['/home']);
      },
      error => {
        console.error('Errore nella creazione dell\'annuncio', error);
      }
    );

  }
  validateImage(){
    const fileInput:any = document.getElementById("imageInput");

    fileInput.addEventListener("change", (event: any) => {
      const file = event.target.files[0]; //prendi file selezionato
      if (file) {
        // Controlla il tipo MIME del file
        if (!file.type.startsWith("image/")) {
          alert("Il file selezionato non è un'immagine!");
          fileInput.value = ""; // Resetta il campo file
        } else {
          console.log("Immagine caricata correttamente")
        }
      }
    });
  }

}
