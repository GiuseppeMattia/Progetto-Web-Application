import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Annuncio } from '../modelli/annuncio.model';
import { AnnuncioService } from '../services/annuncio.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  standalone: true,
  imports: [CommonModule, RouterLink],
  providers: [AnnuncioService]
})
export class HomeComponent implements OnInit {
  annunci: Annuncio[] = [];
  errorMessage = '';

  constructor(private annuncioService: AnnuncioService) {}

  ngOnInit() {
    this.loadAnnunci();
  }

  loadAnnunci() {
    this.annuncioService.getAnnunci().subscribe(
      (annunci) => {
        this.annunci = annunci;
      },
      (error) => {
        console.error('Errore nel caricamento degli annunci:', error);
        this.errorMessage = 'Impossibile caricare gli annunci. Si prega di riprovare più tardi.';
      }
    );
  }
}
