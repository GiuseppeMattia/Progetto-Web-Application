import { Component, OnInit } from '@angular/core';
import { UserModel } from '../modelli/userModel';
import {Router, RouterLink} from '@angular/router';
import { UtenteService } from '../services/utente.service';
import { NgClass, NgForOf, CommonModule } from '@angular/common';

@Component({
  selector: 'app-lista-utenti',
  standalone: true,
  imports: [
    CommonModule,
    NgForOf,
    NgClass,
    RouterLink,
  ],
  templateUrl: './lista-utenti.component.html',
  styleUrl: './lista-utenti.component.css'
})
export class ListaUtentiComponent implements OnInit {
  utenti: UserModel[] = [];

  constructor(private api: UtenteService) {}

  ngOnInit(): void {
    this.caricaUtenti();
  }

  caricaUtenti(): void {
    this.api.trovaUtenti().subscribe(data => {
      this.utenti = data;
    });
  }

  ban(utente: UserModel) {
    utente.bannato = true;
    this.api.ban(utente).subscribe()
  }

  setAdmin(utente: UserModel) {
    if (utente.amministratore) {
      alert("L'utente selezionato è già amministratore");
      return;
    }

    utente.amministratore = true;
    this.api.promuovi(utente).subscribe()
  }
}
