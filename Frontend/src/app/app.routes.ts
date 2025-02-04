import  { Routes } from "@angular/router"
import { LoginComponent } from "./login/login.component"
import { RegisterComponent } from "./register/register.component"
import { HomeComponent } from "./home/home.component"
import {CategorieComponent} from './categorie/categorie.component';
import {ListaUtentiComponent} from './lista-utenti/lista-utenti.component';
import {ProfiloComponent} from './profilo/profilo.component';
import {AnnuncioComponent} from './annuncio/annuncio.component';
import {PannelloAdminComponent} from './pannello-admin/pannello-admin.component';
import {ListaAnnunciComponent} from './lista-annunci/lista-annunci.component';
import {ListaAsteComponent} from './lista-aste/lista-aste.component';
import {CreaComponent} from './crea/crea.component';
import {BannedComponent} from './banned/banned.component';
import {CategoriaAnnunciComponent} from './categoriaannunci/categoriaannunci.component';
import {AuthGuard} from './guards/auth.guard';

export const routes: Routes = [
  { path: "register", component: RegisterComponent },
  { path: "login", component: LoginComponent },
  { path: "home", component: HomeComponent, canActivate:[AuthGuard] },
  { path: "", redirectTo: "/home", pathMatch: "full"},
  { path: "categories", component: CategorieComponent, canActivate: [AuthGuard]},
  { path: "adminBoard", component: PannelloAdminComponent, canActivate: [AuthGuard]},
  { path: "profile", component: ProfiloComponent, canActivate:[AuthGuard]},
  { path: "annuncio/:id", component: AnnuncioComponent, canActivate:[AuthGuard]},
  { path: "listaUtenti", component: ListaUtentiComponent, canActivate:[AuthGuard]},
  { path: "listaAnnunci", component: ListaAnnunciComponent, canActivate:[AuthGuard]},
  { path: "listaAste", component: ListaAsteComponent, canActivate:[AuthGuard]},
  { path: "creaAnnuncio", component: CreaComponent, canActivate:[AuthGuard]},
  { path: "banned", component: BannedComponent },
  { path: 'categoria/:id', component: CategoriaAnnunciComponent, canActivate:[AuthGuard] },
//aggiungi guard per listaX che verifica login e se sei admin
  //guard che controlla login per creaAnnuncio e profile
]

