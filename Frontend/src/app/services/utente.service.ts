import { Injectable } from "@angular/core"
import type { HttpClient } from "@angular/common/http"
import type { Observable } from "rxjs"
import type { Utente } from "../models/utente"

@Injectable({
  providedIn: "root",
})
export class UtenteService {
  private apiUrl = "http://localhost:8080"

  constructor(private http: HttpClient) {}

  validaUtente(utente: Utente): Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/valida`, utente)
  }
  // @ts-ignore
  verificaUsername(string: string): Observable<boolean> {
    fetch(`${this.apiUrl}/verificaUsername=${encodeURIComponent(string)}`)
      .then(response=>{
        if(!response.ok){
          return response.json();
        }
        throw new Error("Utente già esistente");
      })
  };
}

