import { Component, OnInit } from '@angular/core';
import { AstaModel } from '../modelli/asta';
import {Router, RouterLink} from '@angular/router';
import { AstaService } from '../services/asta.service';
import { NgForOf } from '@angular/common';

@Component({
  selector: 'app-lista-aste',
  standalone: true,
  imports: [NgForOf, RouterLink],
  templateUrl: './lista-aste.component.html',
  styleUrls: ['./lista-aste.component.css']
})
export class ListaAsteComponent implements OnInit {
  aste: AstaModel[] = [];

  constructor(private router: Router, private astaService: AstaService) {}

  ngOnInit(): void {
    this.astaService.getAste().subscribe(data => {
      this.aste = data;
    });
  }

  eliminaAsta(asta: AstaModel) {
    if (confirm(`Sei sicuro di voler eliminare l'asta "${asta.id}"?`)) {
      this.astaService.eliminaAsta(asta).subscribe(() => {
        this.aste = this.aste.filter(a => a.id !== asta.id);
        console.log('Asta eliminata!');
      });
    }
  }

}
