import { Injectable } from "@angular/core";
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import { AuthService } from "../services/auth.service";
import {UtenteService} from '../services/utente.service';

@Injectable({
  providedIn: "root",
})
export class BannedGuard implements CanActivate {
  isBanned:boolean=false;
  constructor(private router: Router, private authService: AuthService, private utenteService :UtenteService ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authService.isLoggedIn() && false ) {
      //L'utente è loggato, verifico se è bannato

    }
    //non è un utente loggato, dunque non ha senso verificare se è bannato o meno => Restituisco true
    //Sarà l'altra guard a bloccarlo
    return true;
  }
}
