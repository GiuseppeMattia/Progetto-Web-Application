import {Component, ElementRef, OnInit, ViewChild} from "@angular/core"
import { FormBuilder,  FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms"
import {Router, RouterLink, RouterOutlet} from "@angular/router"

import  { UtenteService } from "../services/utente.service"
import {UserModel} from '../modelli/userModel';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterOutlet,
    ReactiveFormsModule
  ],
  providers:[UtenteService],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'

})

export class RegisterComponent implements OnInit{
  registerForm: FormGroup
  @ViewChild('captchaButton', { static: false }) captchaButton!: ElementRef;
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private utenteService: UtenteService,
  ) {
    this.registerForm = this.fb.group({
      username: ["", Validators.required],
      email: ["", [Validators.required, Validators.email]],
      password: ["", [Validators.required, Validators.minLength(6)]],
      tipo: [false, Validators.required],
      amministratore:false,
      prefisso: ['+39', Validators.required],
      telefono: ['', [Validators.required, Validators.pattern('^[0-9]{6,15}$')]],
      bannato:false
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

    if (this.registerForm.valid) {
      (window as any).grecaptcha.render(); // Attiva il controllo reCAPTCHA invisibile
    } else {
      alert("Per favore, compila tutti i campi richiesti.")
    }
  }

  onSubmitCaptcha(token: string) {
    this.utenteService.mandaCaptcha(token).subscribe(
      {
        next: (response) => {
          if (response.success) {
            this.registerUser(); // Se il captcha è valido, procedi con la registrazione
          } else {
            alert("Verifica reCAPTCHA non riuscita");
          }
        },
        error: (error) => {
          alert("Errore nel contattare il server")
          this.inizializzaReCaptcha();
        }
      }
    )
  }

  fullPhoneNumber(): string {
    const prefix = this.registerForm.get('prefisso')?.value;
    const phone = this.registerForm.get('telefono')?.value;
    return `${prefix}${phone}`;
  }

  registerUser() {
    if (this.registerForm.valid) {
      const username = this.registerForm.get("username")?.value || '';
      const password = this.registerForm.get("password")?.value || '';
      const tipo = this.registerForm.get("tipo")?.value || false;
      const email = this.registerForm.get("email")?.value || '';
      const amministratore = this.registerForm.get("amministratore")?.value || false;
      const bannato = this.registerForm.get("bannato")?.value || false;
      const numero = this.fullPhoneNumber();

      const newUser = new UserModel(username, password, tipo, email, amministratore, bannato, numero);
      this.utenteService.creaUtente(newUser).subscribe(
        {
          next: (response) => {
            this.router.navigate(["/home"])
          },
          error: (error) => {
            alert("Si è verificato un errore sconosciuto")
          }
        }
      )
    } else {
      alert("Per favore, compila tutti i campi richiesti correttamente.")
    }
    this.inizializzaReCaptcha()
  }

}

