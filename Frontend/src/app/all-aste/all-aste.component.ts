import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {Asta} from '../modelli/asta';
import {AstaService} from '../services/asta.service';
import {DomSanitizer, SafeUrl} from '@angular/platform-browser';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-all-aste',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    RouterLink
  ],
  templateUrl: './all-aste.component.html',
  styleUrl: './all-aste.component.css'
})
export class AllAsteComponent implements OnInit{
  aste: Asta[] = [];
  errorMessage = '';

  constructor(
    private astaService: AstaService,
    private sanitizer: DomSanitizer,
  ) {}

  ngOnInit() {
    this.loadAste();
  }

  loadAste() {
     {
      this.astaService.getAste().subscribe({
        next: (aste) => {
          this.aste = aste.filter(asta => !asta.annuncio.venditore.bannato);
          if (this.aste.length == 0){
            this.errorMessage= "Non ci sono aste al momento"
          }
        },
        error: (error) => {
         this.errorMessage='Errore nel caricamento delle aste.';
        }
      });
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

