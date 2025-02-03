import { Injectable } from "@angular/core"
import { BehaviorSubject, type Observable } from "rxjs"
import { map } from "rxjs/operators"
import  { UserModel } from "../modelli/userModel"

@Injectable({
  providedIn: "root",
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<UserModel | null>
  public currentUser: Observable<UserModel | null>
  public authState$: Observable<boolean>
  public isAdmin$: Observable<boolean>
  public isSeller$: Observable<boolean>
  public isBanned$: Observable<boolean>


  constructor() {
    this.currentUserSubject = new BehaviorSubject<UserModel | null>(
      JSON.parse(localStorage.getItem("currentUser") || "null"),
    )
    this.currentUser = this.currentUserSubject.asObservable()
    this.authState$ = this.currentUser.pipe(map((user) => !!user))
    this.isAdmin$ = this.currentUser.pipe(map((user) => !!user?.amministratore))
    this.isSeller$ = this.currentUser.pipe(map((user) => !!user?.venditore))
    this.isBanned$ = this.currentUser.pipe(map((user) => !!user?.bannato))
  }

  public get currentUserValue(): UserModel | null {
    return this.currentUserSubject.value
  }

  login(user: UserModel) {

    localStorage.setItem("currentUser", JSON.stringify(user))
    this.currentUserSubject.next(user)
  }

  logout() {
    localStorage.removeItem("currentUser")
    this.currentUserSubject.next(null)
  }

  isLoggedIn(): boolean {
    return !!this.currentUserValue
  }

}

