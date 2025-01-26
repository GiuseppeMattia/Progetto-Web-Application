import {Component, HostListener} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router, RouterLink, RouterOutlet} from '@angular/router';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterOutlet,
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  constructor(private router: Router,
              private authService: AuthService,) {}


  registerForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
  });
  validateUser() {
    let username = this.registerForm.get('username')?.value ?? '';
    let email = this.registerForm.get('email')?.value ?? '';
    let password = this.registerForm.get('password')?.value ?? '';

    // Se l'username è vuoto, lo dice
    if (!username.trim()) {
      alert("Errore: Il campo 'username' è vuoto");
      return;
    }

    // Se l'username è vuoto, lo dice
    if (!email.trim()) {
      alert("Errore: Il campo 'email' è vuoto");
      return;
    }

    // Se la password è vuota, lo dice
    if (!password.trim()) {
      alert("Errore: Il campo 'password' è vuoto");
      return;
    }

    this.authService.login(username);
    this.router.navigate(['/']);

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
    return this.registerForm.dirty;
  }

  ngOnInit(): void {
  }

}
