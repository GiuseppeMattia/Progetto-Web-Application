import { Component } from "@angular/core"
import { FormBuilder,  FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms"
import {Router, RouterLink, RouterOutlet, Routes} from "@angular/router"
import { HttpClientModule } from "@angular/common/http"

import  { UtenteService } from "../services/utente.service"
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    HttpClientModule,
    FormsModule,
    RouterLink,
    RouterOutlet,
    ReactiveFormsModule
  ],
  providers:[UtenteService],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'

})

export class RegisterComponent {
  registerForm: FormGroup

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private utenteService: UtenteService,
    private authService: AuthService,
  ) {
    this.registerForm = this.fb.group({
      username: ["", Validators.required],
      email: ["", [Validators.required, Validators.email]],
      password: ["", [Validators.required, Validators.minLength(6)]],
      tipo: [false, Validators.required],
      amministratore:false
    })
  }


  registerUser() {
    if (this.registerForm.valid) {
      const newUser = this.registerForm.value
      this.utenteService.creaUtente(newUser).subscribe(
        (response) => {
          console.log("Utente registrato con successo", response)
          this.router.navigate(["/home"])
        },
        (error) => {
          console.error("Errore durante la registrazione:", error)
          alert("Si è verificato un errore durante la registrazione")
        },
      )
    } else {
      alert("Per favore, compila tutti i campi richiesti correttamente.")
    }
  }
}

