import { Component } from '@angular/core';
import {UserModel} from '../modelli/userModel';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-lista-utenti',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './lista-utenti.component.html',
  styleUrl: './lista-utenti.component.css'
})
export class ListaUtentiComponent {
  //array di utenti da popolare con chiamata al backend
  utenti: UserModel[]=[
    new UserModel("Nico",true,"ciao@prova.it",false),
    new UserModel("Giuseppe",false,"giuseppe@prova.it",false),
    new UserModel("Watcher",false,"guardo@prova.it",true),
    new UserModel("Joel",true,"joelOriginal@prova.it",true)
  ]
  ban(utente: UserModel){
    //chiamata al back per bannare l'utente
    console.log("Utente bannato!")
  }
  setAdmin(utente: UserModel){
    if (utente.admin){
      alert("L'utente selezionato è già amministratore")
      return
    }
    //chiamata al back per promuovere un utente ad amministratore
    console.log("Utente promosso")
  }
}
