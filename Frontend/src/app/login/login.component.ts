import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from "@angular/forms"; // Rimosso "type"
import {Router, RouterLink, RouterOutlet} from "@angular/router"; // Rimosso "type"
import { CommonModule } from "@angular/common";
import { UtenteService } from "../services/utente.service"; // Rimosso "type"
import { UserModel } from "../modelli/userModel"; // Rimane "type" perché è solo un'interfaccia
import {provideHttpClient, HttpClient, HttpClientModule} from "@angular/common/http";
import {AuthService} from '../services/auth.service';



@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
  providers:[UtenteService],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterOutlet, HttpClientModule, RouterLink],
})
export class LoginComponent {
  loginForm: FormGroup

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private utenteService: UtenteService,
    private authService: AuthService,
  ) {
    this.loginForm = this.fb.group({
      username: ["", Validators.required],
      password: ["", Validators.required],
    })
  }

  validateUser() {
    if (this.loginForm.valid) {
      const utente: UserModel = {
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
            if(utente.amministratore){
              console.log("Utente amministratore")
            }
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

