export class UserModel{
  constructor(public username: string,public type: boolean,public email: string,public admin:boolean) {
    //type=true per i venditori, false per gli acquirenti
  }
}
