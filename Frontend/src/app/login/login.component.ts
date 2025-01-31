import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from "@angular/forms"; // Rimosso "type"
import { Router, RouterOutlet } from "@angular/router"; // Rimosso "type"
import { CommonModule } from "@angular/common";
import { UtenteService } from "../services/utente.service"; // Rimosso "type"
import { Utente } from "../models/utente"; // Rimane "type" perché è solo un'interfaccia



@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterOutlet],
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
    })
  }

  validateUser() {
    if (this.loginForm.valid) {
      const utente: Utente = {
        username: this.loginForm.get("username")?.value,
        password: this.loginForm.get("password")?.value,
        tipo:false,
        amministratore:false,
        email:''
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

