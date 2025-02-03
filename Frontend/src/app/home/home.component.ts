import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import { Annuncio } from '../modelli/annuncio.model';
import { AnnuncioService } from '../services/annuncio.service';
import {AuthService} from '../services/auth.service';

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
  isSeller: boolean = false;


  constructor(private annuncioService: AnnuncioService,
              public authService: AuthService,
              public router: Router,) {}

  ngOnInit() {

    this.authService.isSeller$.subscribe(isSeller => {
      this.isSeller=isSeller;
    });
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
