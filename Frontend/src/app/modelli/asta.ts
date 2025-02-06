
import {UserModel} from './userModel';
import {Annuncio} from './annuncio.model';

export class Asta {
  constructor(public id:number,
              public annuncio:Annuncio,
              public prezzo:number,
              public acquirente:UserModel |null,
              public terminated:boolean,
              public scadenza: Date
  ) {
  }
}
