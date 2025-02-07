import { Injectable } from "@angular/core";
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import { AuthService } from "../services/auth.service";
import { UtenteService } from "../services/utente.service";
import {Observable, of, switchMap} from "rxjs";
import { map, catchError } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class BanGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService, private utenteService: UtenteService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.authService.currentUser.pipe(
      map(user => user?.username),
      switchMap(username => {
        if (!username) {
          //se non sono loggato non posso essere bannato, sarà compito di altre classi verificare il login dove necessario
          return of(true);
        }
        return this.utenteService.checkBan(username).pipe(
          map(isBanned => {
            if (isBanned) {
              this.authService.logout();
              this.router.navigate(["/banned"]);
              return false;
            }

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

