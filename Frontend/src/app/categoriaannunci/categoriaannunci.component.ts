import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { AnnuncioService } from '../services/annuncio.service';
import { Annuncio } from '../modelli/annuncio.model';

@Component({
  selector: 'app-categoria-annunci',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './categoriaannunci.component.html',
  styleUrls: ['./categoriaannunci.component.css']
})
export class CategoriaAnnunciComponent implements OnInit {
  annunci: Annuncio[] = [];
  categoriaId: number = 0;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private annuncioService: AnnuncioService
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.categoriaId = +params['id'];
      this.loadAnnunci();
    });
  }

  loadAnnunci() {
    this.annuncioService.trovaAnnunciByCategoria(this.categoriaId).subscribe(
      (annunci) => {
        this.annunci = annunci;
      },
      (error) => {
        console.error('Errore nel caricamento degli annunci:', error);
        this.errorMessage = 'Impossibile caricare gli annunci. Si prega di riprovare più tardi.';
      }
    );
  }

  getImageUrl(foto: string): string {
    // Implementa la logica per ottenere l'URL dell'immagine
    return foto;
  }
}
