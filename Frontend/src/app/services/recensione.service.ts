import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Annuncio} from '../modelli/annuncio.model';
import {Observable} from 'rxjs';
import {Recensione} from '../modelli/Recensione';

@Injectable({
  providedIn: 'root'
})
export class RecensioneService {
  private apiUrl = "http://localhost:8080/api/recensioni"

  constructor(private http: HttpClient) {}

  creaRecensione(recensione: Recensione):Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/crea`, recensione);
  }
}
