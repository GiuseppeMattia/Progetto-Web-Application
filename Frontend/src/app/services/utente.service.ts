import { Injectable } from "@angular/core"
import { HttpClient } from "@angular/common/http"

import { type Observable, throwError } from "rxjs"
import { catchError } from "rxjs/operators"
import type { Utente } from "../models/utente"
import {Router} from '@angular/router';

@Injectable({
  providedIn: "root",
})
export class UtenteService {
  private apiUrl = "http://localhost:8080/api/utente"

  constructor(private http: HttpClient, private router: Router) {}

  creaUtente(utente: Utente): Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/crea`, utente)
  }

  validaUtente(utente: Utente): Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/valida`, utente)
  }

}

