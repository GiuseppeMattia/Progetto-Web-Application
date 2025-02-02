import {Categoria} from './categoria';
import {UserModel} from './userModel';
import {Recensione} from './Recensione';

export class Annuncio {
  constructor(public id:number,
              public categoria:Categoria,
              public marca:string,
              public modello:string,
              public prezzo:number,
              public descrizione: string,
              public titolo: string,
              public prezzo_scontato:number,
              public venditore: UserModel,
              public foto: string,
              public recensioni: Recensione[]//string?
              ) {
  }
}
