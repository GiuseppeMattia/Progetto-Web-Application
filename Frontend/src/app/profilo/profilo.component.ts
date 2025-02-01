import { Component } from '@angular/core';
import {UserModel} from '../modelli/userModel';
import {NgIf} from '@angular/common';
import {RouterLink, RouterOutlet} from '@angular/router';

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
export class ProfiloComponent {
  utente=new UserModel("Mario Doccia","prova",false,"docciamario@prova.it",true)
  //il comportamento reale prenderà dalla sessione le informazioni necessarie, ovvero il tipo d'utente
  //in modo da diversificare il profilo
}
