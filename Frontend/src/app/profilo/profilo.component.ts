import {Component} from '@angular/core';
import {UserModel} from '../modelli/userModel';
import {NgIf} from '@angular/common';
import {RouterLink, RouterOutlet} from '@angular/router';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-profilo',
  standalone: true,
  imports: [
    NgIf,
    RouterOutlet,
    RouterLink
  ],
  templateUrl: './profilo.component.html',
  styleUrl: './profilo.component.css'
})
export class ProfiloComponent{
  public utente :UserModel |null
  constructor(private service:AuthService) {
    this.utente=this.service.currentUserValue
  }

}
