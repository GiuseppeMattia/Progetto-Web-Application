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

  aggiornaUtente(utente: UserModel): Observable<UserModel> {
    return this.http.get<UserModel>(`${this.apiUrl}/trovaByUsername?username=${utente.username}`);
  }

  validaUtente(utente: UserModel): Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/valida`, utente).pipe(
      tap((isValid) => {
        if (isValid) {
          // Prima otteniamo i dati aggiornati dell'utente
          this.aggiornaUtente(utente).subscribe(
            (utenteAggiornato) => {
              // Poi facciamo il login con i dati aggiornati
              this.authService.login(utenteAggiornato)
              console.log('Utente aggiornato:', utenteAggiornato);
            },
            (error) => {
              console.error('Errore durante l\'aggiornamento dell\'utente:', error);
              // In caso di errore, procediamo comunque con il login usando i dati originali
              this.authService.login(utente)
            }
          );
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
          item.venditore,
          item.email,
          item.amministratore,
          item.bannato,
          item.telefono
        ))
      )
    );
  }
  promuovi(utente:UserModel): Observable<boolean>{
    return this.http.post<boolean>(`${this.apiUrl}/aggiorna?amministratore=true`,utente)
  }
  ban(utente:UserModel){
  }

  checkBan(){
    return this.authService.isBanned$;
  }
  //chiamata al backend per verificare il captcha
  mandaCaptcha(token: String){
  return this.http.post<{ success: boolean }>('http://localhost:8080/api/captcha/verify-recaptcha', { token })
  }
}

