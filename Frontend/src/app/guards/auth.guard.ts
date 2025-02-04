import { Injectable } from "@angular/core";
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import { AuthService } from "../services/auth.service";
import {map} from 'rxjs/operators';
import {UtenteService} from '../services/utente.service';

@Injectable({
  providedIn: "root",
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService, private utenteService:UtenteService) {}
  //guardia unica, un utente è autorizzato ad accedere alle pagine se è loggato e se non è bannato
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authService.isLoggedIn()) {
      console.log("Utente loggato, sto valutando...")
      this.authService.currentUser.pipe(
        map(user => {
          if (!user){
            console.log("Non trovo l'utente, restituisco false")
            return false; //non trovo un utente, non è autenticato e non può entrare
          }
          // Controlla se l'utente è bannato
          return this.utenteService.checkBan(user.username).pipe(
            map(isBanned => {
              if (isBanned) {
                console.log("Utente bannato, restituisco false")
                //se entro qui l'utente è loggato ma è bannato e viene reindirizzato alla pagina apposita
                this.authService.logout();
                this.router.navigate(['/banned']);
                return false;
              }
              console.log("Ho superato tutti i controlli, puoi entrare")
              return true;
            })
          );
        })
      );
    }

    // Utente non autenticato, reindirizza al login
    console.log("Non sei autenticato, restituisco false")
    this.router.navigate(["/login"], { queryParams: { returnUrl: state.url } });
    return false;
  }
}
