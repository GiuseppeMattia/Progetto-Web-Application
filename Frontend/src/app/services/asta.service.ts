import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Asta } from '../modelli/asta';

@Injectable({
  providedIn: 'root',
})
export class AstaService {
  private apiUrl = 'http://localhost:8080/api/asta'; // Modifica l'URL del backend per le aste

  constructor(private http: HttpClient) {}

  getAsta(id: number): Observable<Asta> {
    return this.http.post<Asta>(`${this.apiUrl}/trovaById`, id);
  }

  getAstaByAnnuncio(id: number){
    return this.http.post<Asta>(`${this.apiUrl}/trovaByAnnuncio`, id);
  }

  getAste(): Observable<Asta[]> {
    return this.http.get<Asta[]>(`${this.apiUrl}/trovaTutte`);
  }

  eliminaAsta(asta: Asta): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/elimina`, { body: asta });
  }

  trovaByUtenteVenditore(username: string): Observable<Asta[]> {
    const params = new HttpParams().set('username', username);
    return this.http.get<Asta[]>(`${this.apiUrl}/trovaByUtenteVenditore`, { params });
  }

  creaAsta(asta: Asta): Observable<boolean>{
    // console.log("Annuncio passato all'asta service:")
    // console.log(asta.annuncio)
    return this.http.post<boolean>(`${this.apiUrl}/crea`, asta)
  }

  aggiornaAsta(asta: Asta){
    return this.http.post<boolean>(`${this.apiUrl}/aggiorna`, asta)
  }

}
