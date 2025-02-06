
import {UserModel} from './userModel';
import {Annuncio} from './annuncio.model';

export class Asta {
  constructor(public ID:number,
              public annuncio:Annuncio,
              public prezzo:number,
              public acquirente:UserModel |null,
              public isTerminated:boolean,
              public scadenza: Date
  ) {
  }
}
