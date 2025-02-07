import {Component, OnInit} from '@angular/core';
import {Annuncio} from '../modelli/annuncio.model';
import {AnnuncioService} from '../services/annuncio.service';
import {DomSanitizer, SafeUrl} from '@angular/platform-browser';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-venditore',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    RouterLink
  ],
  templateUrl: './venditore.component.html',
  styleUrl: './venditore.component.css'
})
export class VenditoreComponent implements OnInit {
  annunci: Annuncio[] = [];
  errorMessage = '';
  username: string|null = null;

  constructor(
    private annuncioService: AnnuncioService,
    private sanitizer: DomSanitizer,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.username = params.get('username');
    });
    this.loadAnnunci();
  }

  loadAnnunci() {
    if (this.username) {
      this.annuncioService.trovaByUtente(this.username).subscribe(
        (annunci) => {
          this.annunci = annunci.filter(annuncio => !annuncio.venditore.bannato);
          //se l'utente è bannato non ne carico gli annunci
        },
        (error) => {
          this.errorMessage = 'Errore nel caricamento degli annunci.';
        }
      );
    } else {
      this.errorMessage = 'Utente non autenticato.';
    }
  }

  getImageUrl(byte: string): SafeUrl {
    const byteCharacters = atob(byte);
    const byteArray = new Uint8Array(byteCharacters.length);

    for (let i = 0; i < byteCharacters.length; i++) {
      byteArray[i] = byteCharacters.charCodeAt(i);
    }

    const blob = new Blob([byteArray], { type: 'image/png' });
    const url = URL.createObjectURL(blob);
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }


}
