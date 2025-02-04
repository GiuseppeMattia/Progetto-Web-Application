import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { AnnuncioService } from '../services/annuncio.service';
import { Annuncio } from '../modelli/annuncio.model';
import {DomSanitizer, SafeUrl} from '@angular/platform-browser';

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
    private annuncioService: AnnuncioService,
    private sanitizer: DomSanitizer
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

  getImageUrl(byte: string): SafeUrl {
    const byteCharacters = atob(byte);
    const byteArray = new Uint8Array(byteCharacters.length);

    for (let i = 0; i < byteCharacters.length; i++) {
      byteArray[i] = byteCharacters.charCodeAt(i);
    }

    const blob = new Blob([byteArray], { type: 'image/png' }); // Tipo MIME corretto
    const url = URL.createObjectURL(blob);
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }
}
