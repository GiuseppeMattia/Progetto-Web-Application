import { Component, type OnInit } from "@angular/core"
import {ActivatedRoute, Router, RouterLink} from "@angular/router"
import {CommonModule, NgIf} from "@angular/common"
import  { Annuncio } from "../modelli/annuncio.model"
import  { AnnuncioService } from "../services/annuncio.service"
import {HttpClientModule} from '@angular/common/http';
import {DomSanitizer, SafeUrl} from '@angular/platform-browser';


@Component({
  selector: "app-annuncio",
  templateUrl: "./annuncio.component.html",
  styleUrls: ["./annuncio.component.css"],
  standalone: true,
  imports: [CommonModule, NgIf, HttpClientModule],
  providers: [AnnuncioService]
})
export class AnnuncioComponent implements OnInit {
  annuncio: Annuncio | null = null;
  errorMessage = ""

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private annuncioService: AnnuncioService,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit() {
    const id  = this.route.snapshot.paramMap.get("id")
    if (id) {
      console.log("ID ricevuto",id)
      this.loadAnnuncio(Number(id))
      //console.log(this.annuncio?.titolo)
    }
  }

  loadAnnuncio(id: number) {
    this.annuncioService.getAnnuncio(id).subscribe(
      (annuncio) => {
        // console.log("Annuncio ricevuto:", annuncio);  // Log per esaminare la risposta
        // console.log(annuncio);
        this.annuncio = annuncio;  // Assicurati di assegnare correttamente l'annuncio
        console.log(annuncio); // Controlla il JSON completo

      },
      (error) => {
        console.error("Errore nel caricamento dell'annuncio:", error);
        this.errorMessage = "Impossibile caricare l'annuncio. Si prega di riprovare più tardi.";
      }
    );
  }

  getImageUrl(byte: string): SafeUrl {
    const byteCharacters = atob(byte);
    const byteArray = new Uint8Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
      byteArray[i] = byteCharacters.charCodeAt(i);
    }
    const blob = new Blob([byteArray], { type: 'image/png' }); // Tipo MIME corretto
    const url = URL.createObjectURL(blob);
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }
}
