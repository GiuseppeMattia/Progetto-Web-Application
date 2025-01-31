import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

interface AuthState {
  isLoggedIn: boolean;
  username: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authStateSubject = new BehaviorSubject<AuthState>({
    isLoggedIn: false,
    username: ''
  });

  authState$ = this.authStateSubject.asObservable();

  login(username: string) {
    this.authStateSubject.next({
      isLoggedIn: true,
      username
    });
  }

  logout() {
    this.authStateSubject.next({
      isLoggedIn: false,
      username: ''
    });
  }
}
