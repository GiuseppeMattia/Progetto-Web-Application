import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from "@angular/core";
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from "@angular/forms"; // Rimosso "type"
import {Router, RouterLink, RouterOutlet} from "@angular/router"; // Rimosso "type"
import { CommonModule } from "@angular/common";
import { UtenteService } from "../services/utente.service"; // Rimosso "type"
import { UserModel } from "../modelli/userModel"; // Rimane "type" perché è solo un'interfaccia
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
        console.log("reCAPTCHA caricato con successo.");
        this.inizializzaReCaptcha();
      };
  }

  inizializzaReCaptcha() {
    if ((window as any).grecaptcha) {
      try {
        (window as any).grecaptcha.ready(() => {
          console.log("reCAPTCHA pronto per l'uso.");
          (window as any).grecaptcha.reset();
        });
      } catch (error) {
        console.error("Errore nell'inizializzare reCAPTCHA:", error);
      }
    } else {
      console.error("grecaptcha non è disponibile.");
    }
  }

  onSubmit(event: Event) {
    event.preventDefault(); // Evita il submit immediato

    if (this.loginForm.valid) {
      (window as any).grecaptcha.render(); // Attiva il reCAPTCHA invisibile
    } else {
      alert("Per favore, compila tutti i campi richiesti.")
    }
  }

  onSubmitCaptcha(token: string) {
    console.log("Token reCAPTCHA ricevuto:", token);
    this.utenteService.mandaCaptcha(token).subscribe(response => {
      if (response.success) {
        console.log("reCAPTCHA verificato con successo");
        this.validateUser(); // Se il reCAPTCHA è valido, procedi con il login
      } else {
        console.error("Verifica reCAPTCHA fallita");
        alert("Verifica reCAPTCHA non riuscita");
      }
    }, error => {
      console.error("Errore nella verifica reCAPTCHA:", error);
      alert("Errore nel contattare il server");
    });
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
                  console.log("UTENTE BANNATO");
                  //alert("Sei stato bannato. Accesso negato.");
                  this.authService.logout();  // Disconnette l'utente bannato
                  this.router.navigate(['/banned']);
                } else {
                  console.log("Login riuscito, utente non bannato");
                  this.authService.login(utenteAggiornato);
                  this.router.navigate(["/home"]);
                }
              },
              (error) => {
                console.error("Errore durante l'aggiornamento dell'utente:", error);
                alert("Errore nel recupero delle informazioni utente");
              }
            );

          } else {
            console.log("Login fallito")
            alert("Username o password non validi")
          }


        },
        (error) => {
          console.error("Errore durante la validazione:", error)
          window.location.reload() //ricarico per l'API
          alert("Si è verificato un errore durante il login")
        },

      )
    } else {
      alert("Per favore, compila tutti i campi richiesti.")
    }

  }

}

