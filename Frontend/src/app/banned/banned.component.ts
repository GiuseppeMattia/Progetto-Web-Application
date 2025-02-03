import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-banned',
  templateUrl: './banned.component.html',
  styleUrls: ['./banned.component.css'],
  standalone: true,
  imports: [CommonModule, RouterLink]
})
export class BannedComponent {}
