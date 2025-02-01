import {Categoria} from './categoria';
import {UserModel} from './userModel';

export class Annuncio {
  constructor(public id:number,
              public categoia:Categoria,
              public marca:string,
              public modello:string,
              public prezzo:number,
              public descrizione: string,
              public titolo: string,
              public prezzo_scontato:number,
              public venditore: UserModel,
              //public foto: any, //string?
              ) {
  }
}
