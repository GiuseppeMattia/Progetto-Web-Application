import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import { Annuncio } from '../modelli/annuncio.model';
import { AnnuncioService } from '../services/annuncio.service';
import { AuthService } from '../services/auth.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-annunci-profilo',
  templateUrl: './annunci-profilo.component.html',
  styleUrls: ['./annunci-profilo.component.css'],
  standalone: true,
  imports: [CommonModule, RouterLink]
})
export class AnnunciProfiloComponent implements OnInit {
  annunci: Annuncio[] = [];
  errorMessage = '';
  username: string = '';

  constructor(
    private annuncioService: AnnuncioService,
    private authService: AuthService,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit() {
    this.loadAnnunci();
  }

  loadAnnunci() {
    const user = this.authService.currentUserValue; // Prende l'utente autenticato
    if (user) {
      this.annuncioService.trovaByUtente(user.username).subscribe({
        next: (annunci) => {
          this.annunci=annunci;
        },
        error: (error) => {
          this.errorMessage='Errore nel caricamento degli annunci.';
        }
      });
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

  eliminaAnnuncio(id: number) {
    const annuncioDaEliminare = this.annunci.find(annuncio => annuncio.id === id);

    if (!annuncioDaEliminare) {
      this.errorMessage = 'Errore: annuncio non trovato.';
      return;
    }

    this.annuncioService.eliminaAnnuncio(annuncioDaEliminare).subscribe({
      next: () => {
        this.annunci=this.annunci.filter(annuncio =>annuncio.id !== id);
      },
      error: (error) => {
        this.errorMessage='Errore nell’eliminazione dell’annuncio.';
      }
    })
  }

}
