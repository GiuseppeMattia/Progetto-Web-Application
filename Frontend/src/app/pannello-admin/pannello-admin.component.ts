import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-pannello-admin',
  templateUrl: './pannello-admin.component.html',
  standalone: true,
  styleUrls: ['./pannello-admin.component.css']
})
export class PannelloAdminComponent {
  constructor(
    public router: Router,
  ) {
  }

  gestisciAnnunci() {
    console.log("Gestione annunci...");
    // Qui puoi navigare a un'altra pagina o eseguire altre azioni
  }

  gestisciAste() {
    console.log("Gestione aste...");
  }

  gestisciUtenti() {
    this.router.navigate(['/listaUtenti']);
    console.log("Gestione utenti...");
  }
}
