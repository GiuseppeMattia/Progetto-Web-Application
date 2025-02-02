
import {UserModel} from './userModel';
import {Annuncio} from './annuncio.model';

export class AstaModel {
  constructor(public id:number,
              public annuncio:Annuncio,
              public prezzo:number,
              public acquirente:UserModel,
              public isTerminated:boolean
  ) {
  }
}
