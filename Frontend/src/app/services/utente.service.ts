import { Injectable } from "@angular/core"
import  { HttpClient } from "@angular/common/http"
import { type Observable, throwError } from "rxjs"
import {catchError, map, tap} from "rxjs/operators"
import  { UserModel } from "../modelli/userModel"
import  { AuthService } from "./auth.service"

@Injectable({
  providedIn: "root",
})
export class UtenteService {
  private apiUrl = "http://localhost:8080/api/utente"

  constructor(
    private http: HttpClient,
    private authService: AuthService,
  ) {}


  validaUtente(utente: UserModel): Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/valida`, utente).pipe(
      tap((isValid) => {
        if (isValid) {
          this.authService.login(utente)
          console.log(utente);
        }
      }),
    )
  }


  creaUtente(utente: UserModel): Observable<any> {
    return this.http.post(`${this.apiUrl}/crea`, utente).pipe(
      tap((response) => {
        // Assumiamo che la registrazione implichi anche il login
        this.authService.login(utente)
      }),
    )
  }
  trovaUtenti():Observable<any>{
    return this.http.get(`${this.apiUrl}/trovaTutti`).pipe(
      map((data:any) =>
        data.map((item:UserModel) => new UserModel(
          item.username,
          item.password,
          item.tipo,
          item.email,
          item.amministratore
        ))
      )
    );
  }
  promuovi(utente:UserModel): Observable<boolean>{
    return this.http.post<boolean>(`${this.apiUrl}/aggiorna?amministratore=true`,utente)
  }
  ban(utente:UserModel){
  }
}

