import { Component } from '@angular/core';
import {Annuncio} from '../modelli/annuncio.model';

@Component({
  selector: 'app-annuncio',
  standalone: true,
  imports: [],
  templateUrl: './annuncio.component.html',
  styleUrl: './annuncio.component.css'
})
export class AnnuncioComponent {
  annuncio: Annuncio=new Annuncio(0,"Prova","Questo è un annuncio di prova",null,"Mario")
  recensioni=[]
  contattaVenditore(){
    console.log("Venditore contattato");
  }
  goRecensioni(){
    console.log("Verrai reindirizzato alla lista delle recensioni")
  }
  addRecensione(){
    console.log("Aggiungi una recensione")
  }
}
