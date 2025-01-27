import { Routes } from '@angular/router';
import {CategorieComponent} from './categorie/categorie.component';
import {HomepageComponent} from './homepage/homepage.component';

export const routes: Routes = [
  {path:'', pathMatch:'full', redirectTo:'/home'},
  {path:'categories', component:CategorieComponent},
  {path:'home', component:HomepageComponent}
];
