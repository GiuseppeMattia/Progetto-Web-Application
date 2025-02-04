import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categoria } from '../modelli/categoria';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
  private apiUrl = 'http://localhost:8080/api/categoria'; // Ensure this matches your backend URL

  constructor(private http: HttpClient) {}

  trovaTutte(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${this.apiUrl}/trovaTutte`).pipe(
      map(response => response.map(cat => new Categoria(cat.id, cat.nome)))
    );
  }

  trovaById(id: number): Observable<Categoria> {
    return this.http.get<Categoria>(`${this.apiUrl}/trovaById?IDCategoria=${id}`);
  }


}
