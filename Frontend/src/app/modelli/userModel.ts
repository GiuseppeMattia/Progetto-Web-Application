export class UserModel{
  constructor(public username: string,
              public password:string,
              public venditore: boolean, //se true l'utente è un venditore, se false acquirente
              public email: string,
              public amministratore:boolean,
              public bannato:boolean,
              public telefono: string) {
  }
}
