import { Component, OnInit } from '@angular/core';
import { Annuncio } from '../modelli/annuncio.model';
import { Router } from '@angular/router';
import { AnnuncioService } from '../services/annuncio.service';
import { NgForOf } from '@angular/common';

@Component({
  selector: 'app-lista-annunci',
  standalone: true,
  imports: [NgForOf],
  templateUrl: './lista-annunci.component.html',
  styleUrls: ['./lista-annunci.component.css']
})
export class ListaAnnunciComponent implements OnInit {
  annunci: Annuncio[] = [];

  constructor(private router: Router, private annuncioService: AnnuncioService) {}

  ngOnInit(): void {
    this.annuncioService.getAnnunci().subscribe(data => {
      this.annunci = data;
    });
  }

  eliminaAnnuncio(annuncio: Annuncio) {
    if (confirm(`Sei sicuro di voler eliminare l'annuncio "${annuncio.titolo}"?`)) {
      this.annuncioService.eliminaAnnuncio(annuncio).subscribe(() => {
        this.annunci = this.annunci.filter(a => a.id !== annuncio.id);
        console.log('Annuncio eliminato!');
      });
    }
  }

  back() {
    this.router.navigate(['/adminBoard']);
  }
}
