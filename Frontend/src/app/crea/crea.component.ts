import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgForOf} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {Annuncio} from '../modelli/annuncio.model';
import {Categoria} from '../modelli/categoria';
import {CategoriaService} from '../services/categoria.service';

@Component({
  selector: 'app-crea',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    RouterLink
  ],
  templateUrl: './crea.component.html',
  styleUrl: './crea.component.css'
})
export class CreaComponent implements OnInit{
  categoriaScelta: number | null =null;
  categorie:Categoria[]=[]
  constructor(private router: Router, private categoriaService: CategoriaService) {}

  ngOnInit(): void {
    this.loadCategorie();
  }
  loadCategorie(){
    this.categoriaService.trovaTutte().subscribe(
      (categorie) => {
        this.categorie = categorie;
        console.log('Categorie caricate:', this.categorie); // For debugging
      },
      (error) => {
        console.error('Errore nel caricamento delle categorie:', error);
      }
    );
  }
  selectAnswer( categoria: number
  ){
    this.categoriaScelta=categoria;
    console.log(this.categoriaScelta)
  }
  createAnnuncio(){

    console.log("Annuncio creato con successo")
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
