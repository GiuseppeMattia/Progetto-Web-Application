import { Injectable } from "@angular/core"
import {  HttpClient, HttpParams } from "@angular/common/http"
import  { Observable } from "rxjs"
import  { Annuncio } from "../modelli/annuncio.model"
import {map} from 'rxjs/operators';
import {UserModel} from '../modelli/userModel';
import {Categoria} from '../modelli/categoria';
import {Recensione} from '../modelli/Recensione';

@Injectable({
  providedIn: "root",
})
export class AnnuncioService {
  private apiUrl = "http://localhost:8080/api/annuncio"

  constructor(private http: HttpClient) {}

  getAnnuncio(id: number): Observable<Annuncio> {
    // Invia l'id come parametro di query, non nel corpo
    const params = new HttpParams().set("id", id.toString());
    return this.http.post<Annuncio>(`${this.apiUrl}/trovaByID`, null, { params });
  }


  trovaAnnunciByCategoria(IDCategoria: number): Observable<Annuncio[]> {
    return this.http.get<Annuncio[]>(`${this.apiUrl}/trovaByCategoria?IDCategoria=${IDCategoria}`);
  }



  getAnnunci(): Observable<Annuncio[]> {
    return this.http.get<any[]>(`${this.apiUrl}/trovaTutti`).pipe(
      map((annunciData: any[]) => {
        return annunciData.map((annuncioData) => {
          const categoria = new Categoria(annuncioData.categoria.id, annuncioData.categoria.nome);
          const venditore = new UserModel(
            annuncioData.venditore.username,
            annuncioData.venditore.password,
            annuncioData.venditore.tipo,
            annuncioData.venditore.email,
            annuncioData.venditore.amministratore,
            annuncioData.venditore.bannato,
            annuncioData.venditore.telefono
          );
          const recensioni = annuncioData.recensioni.map((recensioneData: any) => {
            return new Recensione(
              recensioneData.id,
              recensioneData.testo,
              new UserModel(
                recensioneData.autore.username,
                recensioneData.autore.password,
                recensioneData.autore.tipo,
                recensioneData.autore.email,
                recensioneData.autore.amministratore,
                recensioneData.autore.bannato,
                recensioneData.autore.telefono
              ),
              new Annuncio(
                annuncioData.id,
                categoria,
                annuncioData.marca,
                annuncioData.modello,
                annuncioData.prezzo,
                annuncioData.descrizione,
                annuncioData.titolo,
                annuncioData.prezzoScontato,
                venditore,
                annuncioData.foto,
                [] // inizialmente nessuna recensione nel costruttore Annuncio
              )
            );
          });

          return new Annuncio(
            annuncioData.id,
            categoria,
            annuncioData.marca,
            annuncioData.modello,
            annuncioData.prezzo,
            annuncioData.descrizione,
            annuncioData.titolo,
            annuncioData.prezzoScontato,
            venditore,
            annuncioData.foto,
            recensioni
          );
        });
      })
    );
  }


  eliminaAnnuncio(annuncio: Annuncio): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/elimina`, { body: annuncio });
  }

  creaAnnuncio(annuncio: Annuncio):Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/crea`, annuncio);
  }

  trovaByUtente(username: string): Observable<Annuncio[]> {
    return this.http.get<Annuncio[]>(`${this.apiUrl}/trovaByUtente?username=${username}`);
  }

  aggiornaAnnuncio(annuncio: Annuncio){
    return this.http.post<Annuncio>(`${this.apiUrl}/aggiornaAnnuncio`, annuncio);
  }



}

