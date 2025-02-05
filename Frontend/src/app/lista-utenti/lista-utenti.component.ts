import { Component, OnInit } from '@angular/core';
import { UserModel } from '../modelli/userModel';
import {Router, RouterLink} from '@angular/router';
import { UtenteService } from '../services/utente.service';
import { NgClass, NgForOf, CommonModule } from '@angular/common'; // ✅ IMPORTA CommonModule

@Component({
  selector: 'app-lista-utenti',
  standalone: true,
  imports: [
    CommonModule, // ✅ AGGIUNTO PER RISOLVERE L'ERRORE DI ngClass
    NgForOf,
    NgClass,
    RouterLink,
  ],
  templateUrl: './lista-utenti.component.html',
  styleUrl: './lista-utenti.component.css'
})
export class ListaUtentiComponent implements OnInit {
  utenti: UserModel[] = [];

  constructor(private router: Router, private api: UtenteService) {}

  ngOnInit(): void {
    this.caricaUtenti();
  }

  caricaUtenti(): void {
    this.api.trovaUtenti().subscribe(data => {
      this.utenti = data;
      // console.log(this.utenti)
    });
  }

  ban(utente: UserModel) {
    utente.bannato = true; // Aggiorna localmente lo stato
    this.api.ban(utente).subscribe(() => {
      console.log("Utente bannato!");
    });
  }

  setAdmin(utente: UserModel) {
    if (utente.amministratore) {
      alert("L'utente selezionato è già amministratore");
      return;
    }

    utente.amministratore = true; // Aggiorna localmente lo stato
    this.api.promuovi(utente).subscribe(() => {
      console.log("Utente promosso!");
    });
  }
}
