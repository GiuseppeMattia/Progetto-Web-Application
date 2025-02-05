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
import {BanGuard} from './guards/ban.guard';
import {authGuard} from './guards/auth.guard';
import {AdminGuard} from './guards/admin.guard';
import {AnnunciProfiloComponent} from './annunci-profilo/annunci-profilo.component';
import {AsteProfiloComponent} from './aste-profilo/aste-profilo.component';
import {ModificaAnnuncioComponent} from './modifica-annuncio/modifica-annuncio.component';
import {VenditoreComponent} from './venditore/venditore.component';

export const routes: Routes = [
  { path: "register", component: RegisterComponent },
  { path: "login", component: LoginComponent },
  { path: "home", component: HomeComponent, canActivate:[BanGuard] },
  { path: "", redirectTo: "/home", pathMatch: "full"},
  { path: "categories", component: CategorieComponent, canActivate: [BanGuard]},
  //in adminBoard non saranno inserite altre guard perché ci sono già dei controlli analoghi
  { path: "adminBoard", component: PannelloAdminComponent, canActivate: [BanGuard]},
  { path: "profile", component: ProfiloComponent, canActivate:[authGuard,BanGuard]},
  { path: "annuncio/:id", component: AnnuncioComponent, canActivate:[BanGuard]},
  { path: "listaUtenti", component: ListaUtentiComponent, canActivate:[authGuard,BanGuard,AdminGuard]},
  { path: "listaAnnunci", component: ListaAnnunciComponent, canActivate:[authGuard,BanGuard,AdminGuard]},
  { path: "listaAste", component: ListaAsteComponent, canActivate:[authGuard,BanGuard,AdminGuard]},
  { path: "creaAnnuncio", component: CreaComponent, canActivate:[authGuard,BanGuard]},
  { path: "banned", component: BannedComponent },
  { path: 'categoria/:id', component: CategoriaAnnunciComponent, canActivate:[authGuard,BanGuard] },
  { path: 'mieiAnnunci', component: AnnunciProfiloComponent, canActivate:[authGuard,BanGuard]},
  { path: 'mieAste', component: AsteProfiloComponent, canActivate:[authGuard,BanGuard]},
  { path: "modificaannuncio/:id", component: ModificaAnnuncioComponent, canActivate:[authGuard, BanGuard]},
  { path: "venditore/:username", component: VenditoreComponent, canActivate:[BanGuard]}
//guardie per venditore/acquirente?
]

