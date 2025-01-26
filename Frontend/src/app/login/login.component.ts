import {Component, HostListener, OnInit} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router, RouterLink, RouterOutlet} from '@angular/router';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  constructor(private router: Router,
              private authService: AuthService,) {}

  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  validateUser() {
    let username = this.loginForm.get('username')?.value ?? '';
    let password = this.loginForm.get('password')?.value ?? '';

    // Se l'username è vuoto, lo dice
    if (!username.trim()) {
      alert("Errore: Il campo 'username' è vuoto");
      return;
    }

    // Se la password è vuota, lo dice
    if (!password.trim()) {
      alert("Errore: Il campo 'password' è vuoto");
      return;
    }

    this.authService.login(username);
    this.router.navigate(['/']);

    // Invia i vari dati al backend
    // this.api.sendUser(username, password).subscribe({
    //   next: () => {
    //     this.auth.setLogged(true);
    //     this.cookieService.set("username", username);
    //     this.router.navigate(["/home"]);
    //     //console.log('Login effettuato con successo');
    //   },
    //   error: (error) => {
    //     if (error.status === 401) { // La password è sbagliata
    //       alert("Errore: Password errata");
    //     } else if (error.status === 404) { // L'utente non esiste
    //       alert("Errore: Utente non trovato");
    //     } else if (error.status === 0) {
    //       alert('Il servizio non è al momento raggiungibile. Riprova più tardi');
    //     } else {
    //       alert("C'è stato un errore nel login")
    //       console.error('Errore sconosciuto: ', error.status);
    //     }
    //   }
    // });

  }

  // Chiede all'utente se è sicuro di uscire dalla pagina dopo i cambiamenti al form
  @HostListener('window:beforeunload', ['$event'])
  unloadNotification($event: any) {
    if (this.isFormDirty()) {
      $event.returnValue = true;
    }
  }

  // Vede che i campi del form siano stati modificati
  isFormDirty(): boolean {
    return this.loginForm.dirty;
  }

  ngOnInit(): void {
  }
}
