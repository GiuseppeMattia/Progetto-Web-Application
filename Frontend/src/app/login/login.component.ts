import { Component } from "@angular/core"
import { type FormBuilder, type FormGroup, Validators, ReactiveFormsModule } from "@angular/forms"
import type { Router } from "@angular/router"
import { CommonModule } from "@angular/common"
import type { UtenteService } from "../services/utente.service"
import type { Utente } from "../models/utente"

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
})
export class LoginComponent {
  loginForm: FormGroup

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private utenteService: UtenteService,
  ) {
    this.loginForm = this.fb.group({
      username: ["", Validators.required],
      password: ["", Validators.required],
      tipo: [false, Validators.required], // false = acquirente come default

    })
  }

  validateUser() {
    if (this.loginForm.valid) {
      const utente: Utente = {
        username: this.loginForm.get("username")?.value,
        password: this.loginForm.get("password")?.value,
        tipo: this.loginForm.get("tipo")?.value,
        email: this.loginForm.get("email")?.value,
        amministratore: this.loginForm.get("admin")?.value,
      }

      this.utenteService.validaUtente(utente).subscribe(
        (isValid) => {
          if (isValid) {
            console.log("Login riuscito")
            this.router.navigate(["/home"])
          } else {
            console.log("Login fallito")
            alert("Username o password non validi")
          }
        },
        (error) => {
          console.error("Errore durante la validazione:", error)
          alert("Si è verificato un errore durante il login")
        },
      )
    } else {
      alert("Per favore, compila tutti i campi richiesti.")
    }
  }
}

