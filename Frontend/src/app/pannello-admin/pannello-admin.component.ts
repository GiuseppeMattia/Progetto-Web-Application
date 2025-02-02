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
    this.router.navigate(["/listaAnnunci"])
  }

  gestisciAste() {
    this.router.navigate(["/listaAste"]);
  }

  gestisciUtenti() {
    this.router.navigate(['/listaUtenti']);
  }
}
