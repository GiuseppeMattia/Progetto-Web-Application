import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AstaService} from '../services/asta.service';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {Asta} from '../modelli/asta';

@Component({
  selector: 'app-modifica-asta',
  standalone: true,
  imports: [
    ReactiveFormsModule // AGGIUNGI IL ROUTER LINK SE C'È QUALCHE BUG QUI
  ],
  templateUrl: './modifica-asta.component.html',
  styleUrl: './modifica-asta.component.css'
})
export class ModificaAstaComponent implements OnInit{
  protected updateForm: FormGroup;
  private asta: Asta | null;

  constructor(private fb: FormBuilder, private router: Router,
              private route: ActivatedRoute, private astaService: AstaService) {
    this.updateForm = this.fb.group({
      data: [null, Validators.required]
    })

    this.asta = null;
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.astaService.getAsta(Number(id)).subscribe(
          (asta) => {
            this.asta = asta;
          },
          (error) => {
            console.log("Errore nel recupero dell'asta", error);
          }
        );
      }
    });
  }

  modifica(){
    if (!this.updateForm.get("data")?.value) {
      alert("Per favore, completa i campi richiesti");
      return;
    }

    if(!this.asta){
      return;
    }

    let oggi = new Date().getTime();


    let asta=new Asta(
      this.asta?.id,
      this.asta?.annuncio,
      this.asta.prezzo,
      this.asta.acquirente,
      this.asta.terminated,
      this.updateForm.get("data")?.value,
    )


    let terminated = (oggi > new Date(asta.scadenza).getTime())

    asta.terminated = terminated;


    this.astaService.aggiornaAsta(asta).subscribe(
      response => {
        alert("L'asta è stata modificata con successo!")
        this.router.navigate(['/home']);
      },
    );
  }

  goBack(){
    this.router.navigate(['/annuncio',this.asta?.annuncio.id]);
  }

}

