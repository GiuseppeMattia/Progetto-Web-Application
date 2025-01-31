import { Component, OnInit } from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import { AuthService } from '../services/auth.service';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  standalone: true,
  imports: [CommonModule, RouterLink]
})
export class NavbarComponent implements OnInit {
  isLoggedIn = false;
  username = '';
  isAdmin=true;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.authService.authState$.subscribe(state => {
      this.isLoggedIn = state.isLoggedIn;
      this.username = state.username;
    });
  }


  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  goToMyListings() {
    this.router.navigate(['/annunci']);
  }
  goAdminDashboard(){
    this.router.navigate(['/adminBoard'])
  }
}
