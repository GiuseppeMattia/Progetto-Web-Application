import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { Asta } from '../modelli/asta';
import { AstaService } from '../services/asta.service';
import { AuthService } from '../services/auth.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-aste-profilo',
  templateUrl: './aste-profilo.component.html',
  styleUrls: ['./aste-profilo.component.css'],
  standalone: true,
  imports: [CommonModule, RouterLink]
})
export class AsteProfiloComponent implements OnInit {
  aste: Asta[] = [];
  errorMessage = '';

  constructor(
    private astaService: AstaService,
    private authService: AuthService,
    private sanitizer: DomSanitizer,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadAste();
  }

  loadAste() {
    const user = this.authService.currentUserValue;
    if (user) {
      this.astaService.trovaByUtenteVenditore(user.username).subscribe(
        (aste) => {
          this.aste = aste;
        },
        () => {
          this.errorMessage = 'Errore nel caricamento delle aste.';
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

  eliminaAsta(id: number) {
    const astaDaEliminare = this.aste.find(asta => asta.ID === id);

    if (!astaDaEliminare) {
      this.errorMessage = 'Errore: asta non trovata.';
      return;
    }

    this.astaService.eliminaAsta(astaDaEliminare).subscribe(
      () => {
        this.aste = this.aste.filter(asta => asta.ID !== id);
      },
      () => {
        this.errorMessage = 'Errore nell’eliminazione dell’asta.';
      }
    );
  }
}
