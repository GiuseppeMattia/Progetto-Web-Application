import { Component } from '@angular/core';
import {UserModel} from '../modelli/userModel';
import {NgForOf} from '@angular/common';
import {Router} from '@angular/router';

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
    new UserModel("Nico","prova",true,"ciao@prova.it",false),
    new UserModel("Giuseppe","prova",false,"giuseppe@prova.it",false),
    new UserModel("Watcher","prova",false,"guardo@prova.it",true),
    new UserModel("Joel","prova",true,"joelOriginal@prova.it",true)
  ]
  constructor(private router: Router) {
  }
  ban(utente: UserModel){
    //chiamata al back per bannare l'utente
    console.log("Utente bannato!")
  }
  setAdmin(utente: UserModel){
    if (utente.amministratore){
      alert("L'utente selezionato è già amministratore")
      return
    }
    //chiamata al back per promuovere un utente ad amministratore
    console.log("Utente promosso")
  }
  back(){
    this.router.navigate(["/profile"])
  }
}
