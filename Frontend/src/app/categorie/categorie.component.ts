import { Component } from '@angular/core';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-categorie',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './categorie.component.html',
  styleUrl: './categorie.component.css'
})
export class CategorieComponent {
  //per adesso hard-coded, l'idea è quella di popolarlo in ngOnInit chiamando il back
  categorie=[
    {id:0, nome:"Display"},
    {id:1, nome:"Telefoni"},
    {id:2, nome:"Schede"},
    {id:3, nome:"Computer"}]
}
