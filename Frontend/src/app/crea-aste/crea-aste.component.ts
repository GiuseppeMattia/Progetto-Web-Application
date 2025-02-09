import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Annuncio} from '../modelli/annuncio.model';
import {Asta} from '../modelli/asta';
import {AstaService} from '../services/asta.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AnnuncioService} from '../services/annuncio.service';

@Component({
  selector: 'app-crea-aste',
  standalone: true,
    imports: [
        ReactiveFormsModule
    ],
  templateUrl: './crea-aste.component.html',
  styleUrl: './crea-aste.component.css'
})
export class CreaAsteComponent implements OnInit{
  protected createForm: FormGroup;
  private annuncio: Annuncio |null;
  constructor(private fb: FormBuilder, private astaService: AstaService, private router: Router,
              private route:ActivatedRoute, private annuncioService: AnnuncioService) {
    this.createForm = this.fb.group({
      prezzo: [null, Validators.required],
      data: [null, Validators.required]
    })
    this.annuncio = null;
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const id = params['id'];
      this.annuncioService.getAnnuncio(id).subscribe(
        (annuncio) => {
          this.annuncio = annuncio;
        },
      )
    });
  }

  crea(){
    // Controllo che i campi obbligatori siano stati inseriti
    if (!this.createForm.get('prezzo')?.value || !this.createForm.get("data")?.value) {
      alert("Per favore, completa i campi richiesti");
      return;
    }

  let asta=new Asta(
    50,
    this.annuncio!,
    this.createForm.get('prezzo')?.value,
    null,
    false,
    this.createForm.get("data")?.value,
  )

    let oggi = new Date();
    let dataInserita = new Date(this.createForm.get("data")?.value); // Converte la stringa in Date

    if (dataInserita.getTime() <= oggi.getTime()) {
      alert("Non puoi inserire una data precedente o uguale alla giornata odierna");
      return;
    }





    this.astaService.creaAsta(asta).subscribe(
      response => {
        alert("L'asta è stata creata con successo!")
        this.router.navigate(['/home']);
      },
    );
  }

  goBack(){
    this.router.navigate(['/annuncio', this.annuncio?.id]);
  }

}
