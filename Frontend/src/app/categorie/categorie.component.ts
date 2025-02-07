import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { CategoriaService } from '../services/categoria.service';
import { Categoria } from '../modelli/categoria';

@Component({
  selector: 'app-categorie',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './categorie.component.html',
  styleUrls: ['./categorie.component.css']
})
export class CategorieComponent implements OnInit {
  categorie: Categoria[] = [];
  errorMessage = '';

  constructor(private categoriaService: CategoriaService) {}

  ngOnInit() {
    this.loadCategorie();
  }

  loadCategorie() {
    this.categoriaService.trovaTutte().subscribe(
      {
        next: (categorie) => {
          this.categorie=categorie;
        },
        error: (error) => {
          console.error('Errore nel caricamento delle categorie:', error);
          this.errorMessage = 'Impossibile caricare le categorie. Si prega di riprovare più tardi.';
        }
      }
    )
  }
}
