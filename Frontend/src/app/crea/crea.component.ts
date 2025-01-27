import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-crea',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf
  ],
  templateUrl: './crea.component.html',
  styleUrl: './crea.component.css'
})
export class CreaComponent {
  categoriaScelta: number | null =null;
  //per adesso hard-coded, l'idea è quella di popolarlo chiamando il back
  categorie=[
    {id:0, nome:"Display"},
    {id:1, nome:"Telefoni"},
    {id:2, nome:"Schede"},
    {id:3, nome:"Computer"}]
  selectAnswer( categoria: number
  ){
    this.categoriaScelta=categoria;
  }
  createAnnuncio(){
    //inizializzazione campi da passare al back
    //chiamata al back
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
