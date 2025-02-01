import {Component, OnInit} from '@angular/core';
import {UserModel} from '../modelli/userModel';
import {NgForOf} from '@angular/common';
import {Router} from '@angular/router';
import {UtenteService} from '../services/utente.service';

@Component({
  selector: 'app-lista-utenti',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './lista-utenti.component.html',
  styleUrl: './lista-utenti.component.css'
})
export class ListaUtentiComponent implements OnInit{
  //array di utenti da popolare con chiamata al backend
  utenti: UserModel[]=[]
  constructor(private router: Router, private api: UtenteService) {
  }

  ngOnInit(): void {
       this.api.trovaUtenti().subscribe(data=>{
         this.utenti=data;
       })
    }
  ban(utente: UserModel){
    //chiamata al back per bannare l'utente
    this.api.ban(utente)
    console.log("Utente bannato!")
  }
  setAdmin(utente: UserModel){
    if (utente.amministratore){
      alert("L'utente selezionato è già amministratore")
      return
    }
    //chiamata al back per promuovere un utente ad amministratore
    this.api.promuovi(utente).subscribe()
    window.location.reload();
    console.log("Utente promosso")
  }
  back(){
    this.router.navigate(["/profile"])
  }
}
