import { Injectable } from "@angular/core";
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import { AuthService } from "../services/auth.service";
import { UtenteService } from "../services/utente.service";
import {Observable, of, switchMap} from "rxjs";
import { map, catchError } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService, private utenteService: UtenteService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.authService.currentUser.pipe(
      map(user => user?.username), // Prendi l'username dell'utente corrente
      switchMap(username => {
        if (!username) {
          console.log("Nome utente non trovato.");
          //se non sono loggato non posso essere bannato, sarà compito di altre classi verificare il login dove necessario
          return of(true);
        }
        return this.utenteService.checkBan(username).pipe(
          map(isBanned => {
            if (isBanned) {
              console.log("Utente bannato, reindirizzo alla pagina apposita.");
              this.authService.logout();
              this.router.navigate(["/banned"]);
              return false;
            }

            console.log("Utente non bannato, accesso consentito.");
            return true;
          }),
          catchError(err => {
            console.error("Errore durante il controllo del ban:", err);
            return of(false);
          })
        );
      })
    );
  }
}

