import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AstaModel } from '../modelli/asta';

@Injectable({
  providedIn: 'root',
})
export class AstaService {
  private apiUrl = 'http://localhost:8080/api/asta'; // Modifica l'URL del backend per le aste

  constructor(private http: HttpClient) {}

  getAsta(id: number): Observable<AstaModel> {
    const params = new HttpParams().set('id', id.toString());
    return this.http.post<AstaModel>(`${this.apiUrl}/trovaByID`, null, { params });
  }

  getAste(): Observable<AstaModel[]> {
    return this.http.get<AstaModel[]>(`${this.apiUrl}/trovaTutte`);
  }

  eliminaAsta(asta: AstaModel): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/elimina`, { body: asta });
  }

  trovaByUtenteVenditore(username: string): Observable<AstaModel[]> {
    const params = new HttpParams().set('username', username);
    return this.http.get<AstaModel[]>(`${this.apiUrl}/trovaByUtenteVenditore`, { params });
  }

}
