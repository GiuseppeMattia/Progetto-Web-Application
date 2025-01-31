import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {CategorieComponent} from './categorie/categorie.component';
import {HomepageComponent} from './homepage/homepage.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CategorieComponent, RouterLink, HomepageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'DeMaCSzon';
}
