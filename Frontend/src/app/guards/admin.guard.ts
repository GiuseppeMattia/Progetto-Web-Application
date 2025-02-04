import { Injectable } from "@angular/core";
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import { AuthService } from "../services/auth.service";
import { UtenteService } from "../services/utente.service";

@Injectable({
  providedIn: "root",
})
export class AdminGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const isAdmin:boolean = this.authService.currentUserValue!.amministratore;
    //verifico che un utente sia admin, se non lo è non entra sulla pagina
    if (!isAdmin) {
      alert("Spiacente, questa pagina è riservata agli amministratori") //notifico l'utente
      this.router.navigate(["/home"])
      return false;
    }
    return true;
  }
}
