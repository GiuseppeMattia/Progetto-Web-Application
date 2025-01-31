export class Utente{
  constructor(public username: string,
              public password: string,
              public tipo: boolean,
              public email: string,
              public amministratore:boolean) {
  }
}
