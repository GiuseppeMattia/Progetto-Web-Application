import {Component, ElementRef, OnInit, ViewChild} from "@angular/core";
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from "@angular/forms";
import {Router, RouterLink, RouterOutlet} from "@angular/router";
import { CommonModule } from "@angular/common";
import { UtenteService } from "../services/utente.service";
import { UserModel } from "../modelli/userModel";
import {AuthService} from '../services/auth.service';



@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
  providers:[UtenteService],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterOutlet, RouterLink],
})
export class LoginComponent implements OnInit{
  loginForm: FormGroup
  @ViewChild('captchaButton', { static: false }) captchaButton!: ElementRef;
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
  ngOnInit() {
      const script = document.createElement('script');
      script.src = 'https://www.google.com/recaptcha/api.js';
      script.async = true;
      script.defer = true;
      document.body.appendChild(script);
      (window as any).onSubmitCaptcha = (token: string) => this.onSubmitCaptcha(token);

      script.onload = () => {
        this.inizializzaReCaptcha();
      };
  }

  inizializzaReCaptcha() {
    if ((window as any).grecaptcha) {
      try {
        (window as any).grecaptcha.ready(() => {
          (window as any).grecaptcha.reset();
        });
      } catch (error) {
        console.error("Errore nell'inizializzare reCAPTCHA:", error);
      }
    }
  }

  onSubmit(event: Event) {
    event.preventDefault();

    if (this.loginForm.valid) {
      (window as any).grecaptcha.render(); // Attiva i controlli di reCAPTCHA invisibile
    } else {
      alert("Per favore, compila tutti i campi richiesti.")
    }
  }

  onSubmitCaptcha(token: string) {
    this.utenteService.mandaCaptcha(token).subscribe(
      {
        next: (response) => {
         if (response.success){
           this.validateUser()
         } else {
           alert ("Verifica reCAPTCHA non riuscita")
         }
        },
        error: (error) => {
          alert("Errore nel contattare il server");
        }
      }
    )
  }

  validateUser() {
    if (this.loginForm.valid) {
      const utente: UserModel = {
        username: this.loginForm.get("username")?.value,
        password: this.loginForm.get("password")?.value,
        venditore:false,
        amministratore:false,
        email:'',
        bannato:false,
        telefono:''
      }

      this.utenteService.validaUtente(utente).subscribe(
        (isValid) => {
          if (isValid) {
            this.utenteService.aggiornaUtente(utente).subscribe(
              (utenteAggiornato) => {
                if (utenteAggiornato.bannato) {
                  this.authService.logout();  // Disconnette l'utente bannato
                  this.router.navigate(['/banned']);
                } else {
                  this.authService.login(utenteAggiornato);
                  this.router.navigate(["/home"]);
                }
              },
              (error) => {
                alert("Errore nel recupero delle informazioni utente");
              }
            );
          } else {
            alert("Username o password non validi")
          }
        },
        (error) => {
          if(error.status === 401){
            alert("Password errata")
          }
          else if(error.status === 404){
            alert("L'utente non esiste")
          }
          else{
            alert("Si è verificato un errore sconosciuto")
          }
        },
      )
    } else {
      alert("Per favore, compila tutti i campi richiesti.")
    }
    this.inizializzaReCaptcha()
  }

}

