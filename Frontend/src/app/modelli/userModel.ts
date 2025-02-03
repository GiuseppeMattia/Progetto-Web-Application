export class UserModel{
  constructor(public username: string,
              public password:string,
              public venditore: boolean,
              public email: string,
              public amministratore:boolean,
              public bannato:boolean,
              public telefono: string) {
    //type=true per i venditori, false per gli acquirenti
    //unificati i modelli utente
    //messi i nomi in italiano per corrispondenza con db
  }
}
