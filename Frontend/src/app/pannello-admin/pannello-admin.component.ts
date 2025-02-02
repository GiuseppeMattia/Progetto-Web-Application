import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-pannello-admin',
  templateUrl: './pannello-admin.component.html',
  standalone: true,
  styleUrls: ['./pannello-admin.component.css']
})
export class PannelloAdminComponent implements OnInit {

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.authService.isAdmin$.subscribe(isAdmin => {
      if (!isAdmin) {
        this.goHome();  // Reindirizza alla home se non è un admin
      }
    });
  }



  // Funzione per reindirizzare alla home
  goHome(): void {
    this.router.navigate(['/home']);
  }

  gestisciAnnunci(): void {
    this.router.navigate(["/listaAnnunci"]);
  }

  gestisciAste(): void {
    this.router.navigate(["/listaAste"]);
  }

  gestisciUtenti(): void {
    this.router.navigate(['/listaUtenti']);
  }
}
