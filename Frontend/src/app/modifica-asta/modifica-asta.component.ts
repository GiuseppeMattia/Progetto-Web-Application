import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AstaService} from '../services/asta.service';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {Asta} from '../modelli/asta';

@Component({
  selector: 'app-modifica-asta',
  standalone: true,
  imports: [
    ReactiveFormsModule
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
            // console.log("asta: ", asta);
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
      // console.log(this.asta)
      return;
    }

    let asta=new Asta(
      this.asta?.ID,
      this.asta?.annuncio,
      this.asta.prezzo,
      this.asta.acquirente,
      this.asta.isTerminated,
      this.updateForm.get("data")?.value,
    )

    console.log("Qua sì")


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

