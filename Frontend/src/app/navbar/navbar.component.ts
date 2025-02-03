import { Component, type OnInit, type OnDestroy } from "@angular/core"
import {  Router, RouterLink } from "@angular/router"
import { AuthService } from "../services/auth.service"
import { CommonModule } from "@angular/common"
import  { Subscription } from "rxjs"

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"],
  standalone: true,
  imports: [CommonModule, RouterLink],
})
export class NavbarComponent implements OnInit, OnDestroy {
  isLoggedIn = false
  isAdmin = false
  username = ""
  private authSubscription: Subscription | undefined

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  ngOnInit() {
    this.authSubscription = this.authService.currentUser.subscribe((user) => {
      this.isLoggedIn = !!user
      this.isAdmin = user?.amministratore || false
      this.username = user?.username || ""
      console.log("Is Admin:", this.isAdmin)
    })
  }

  ngOnDestroy() {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe()
    }
  }

  logout() {
    this.authService.logout()
    this.router.navigate(["/home"])
    window.location.reload(); //forzi il reload in modo tale da evitare problemi per l'API
  }
  goLogin(){
    this.router.navigate(["/login"])
  }
  goRegister(){
    this.router.navigate(["/register"])
  }
}

