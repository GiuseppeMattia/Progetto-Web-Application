import  { Routes } from "@angular/router"
import { LoginComponent } from "./login/login.component"
import { RegisterComponent } from "./register/register.component"
import { HomeComponent } from "./home/home.component"
import {CategorieComponent} from './categorie/categorie.component';
import {ListaUtentiComponent} from './lista-utenti/lista-utenti.component';
import {ProfiloComponent} from './profilo/profilo.component';
import {AnnuncioComponent} from './annuncio/annuncio.component';
import {PannelloAdminComponent} from './pannello-admin/pannello-admin.component';


export const routes: Routes = [
  { path: "register", component: RegisterComponent },
  { path: "login", component: LoginComponent },
  { path: "home", component: HomeComponent },
  { path: "", redirectTo: "/home", pathMatch: "full" },
  { path: "categories", component: CategorieComponent},
  { path: "adminBoard", component: PannelloAdminComponent},
  { path: "profile", component: ProfiloComponent},
  { path: "annuncio/:id", component: AnnuncioComponent},
  { path: "listaUtenti", component: ListaUtentiComponent},
]

