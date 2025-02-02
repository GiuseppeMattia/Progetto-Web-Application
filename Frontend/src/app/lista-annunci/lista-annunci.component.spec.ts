import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaAnnunciComponent } from './lista-annunci.component';

describe('ListaAnnunciComponent', () => {
  let component: ListaAnnunciComponent;
  let fixture: ComponentFixture<ListaAnnunciComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaAnnunciComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaAnnunciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
