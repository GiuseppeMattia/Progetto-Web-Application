import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { AnnuncioService } from '../services/annuncio.service';
import { Annuncio } from '../modelli/annuncio.model';
import {DomSanitizer, SafeUrl} from '@angular/platform-browser';
import {AstaService} from '../services/asta.service';
import {Asta} from '../modelli/asta';

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
  asteMap: Map<number, Asta> = new Map(); // Mappa per associare ad ogni annuncio un' asta

  constructor(
    private route: ActivatedRoute,
    private annuncioService: AnnuncioService,
    private sanitizer: DomSanitizer,
    private astaService: AstaService
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.categoriaId = +params['id'];
      this.loadAnnunciEAste();
    });
  }

  loadAnnunciEAste() {
    this.annuncioService.trovaAnnunciByCategoria(this.categoriaId).subscribe(
      (annunci) => {
        this.annunci = annunci;
        this.annunci.forEach(annuncio => {
          this.astaService.getAstaByAnnuncio(annuncio.id).subscribe(
            (asta) => {
              this.asteMap.set(annuncio.id, asta);
            },
            (error) => {
              if (error.status !== 404) {
                console.error('Errore nel caricamento dell\'asta per l\'annuncio', annuncio.id);
              }
            }
          );
        });
      },
      (error) => {
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
