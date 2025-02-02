import { Injectable } from "@angular/core"
import {  HttpClient, HttpParams } from "@angular/common/http"
import  { Observable } from "rxjs"
import  { Annuncio } from "../modelli/annuncio.model"

@Injectable({
  providedIn: "root",
})
export class AnnuncioService {
  private apiUrl = "http://localhost:8080/api/annuncio" // Assicurati che questo sia l'URL corretto del tuo backend

  constructor(private http: HttpClient) {}

  getAnnuncio(id: number): Observable<Annuncio> {
    // Invia l'id come parametro di query, non nel corpo
    const params = new HttpParams().set("id", id.toString());
    return this.http.post<Annuncio>(`${this.apiUrl}/trovaByID`, null, { params });
  }

  getAnnunci(): Observable<Annuncio[]> {
    return this.http.get<Annuncio[]>(`${this.apiUrl}/trovaTutti`);
  }



}

