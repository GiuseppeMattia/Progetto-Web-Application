import { Routes } from '@angular/router';
import {CategorieComponent} from './categorie/categorie.component';
import {HomepageComponent} from './homepage/homepage.component';
import {ListaUtentiComponent} from './lista-utenti/lista-utenti.component';
import {CreaComponent} from './crea/crea.component';

export const routes: Routes = [
  {path:'', pathMatch:'full', redirectTo:'/home'},
  {path:'categories', component:CategorieComponent},
  {path:'home', component:HomepageComponent},
  {path:'users',component:ListaUtentiComponent},
  {path:'create',component: CreaComponent}
];
