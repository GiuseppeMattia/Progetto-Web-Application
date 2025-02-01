export class Annuncio {
  constructor(public id:number,
              public titolo: string,
              public descrizione: string,
              public foto: any, //string?
              public venditore: string) {
  }
}
