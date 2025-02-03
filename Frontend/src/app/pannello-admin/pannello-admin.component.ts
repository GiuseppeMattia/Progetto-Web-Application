import { Component, OnInit } from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-pannello-admin',
  templateUrl: './pannello-admin.component.html',
  standalone: true,
  imports: [
    RouterLink
  ],
  styleUrls: ['./pannello-admin.component.css']
})
export class PannelloAdminComponent implements OnInit {

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.authService.isAdmin$.subscribe(isAdmin => {
      if (!isAdmin) {
        this.router.navigate(['/home']);
      }
    });
  }


}
