import {UserModel} from './userModel';
import {Annuncio} from './annuncio.model';

export class Recensione {
  constructor(public id:string,
              public testo:string,
              public autore: UserModel,
              public annuncio:Annuncio
  ) {
  }
}
