import { Injectable } from "@angular/core";
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import { AuthService } from "../services/auth.service";
import { UtenteService } from "../services/utente.service";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class BannedGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService, private utenteService: UtenteService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    if (this.authService.isLoggedIn()) {
      // Ottieni l'utente corrente
      this.authService.currentUser.pipe(
        map(user => {
          if (!user) return true; // Non c'è un utente, ok (sarà compito dell'AuthGuard impedire l'accesso)

          // Controlla se l'utente è bannato
          return this.utenteService.checkBan(user.username).pipe(
            map(isBanned => {
              if (isBanned) {
                this.authService.logout();
                this.router.navigate(['/banned']);
                return false;
              }
              return true;
            })
          );
        })
      );
    }
    return true; //Non è loggato, non controllo
  }
}

