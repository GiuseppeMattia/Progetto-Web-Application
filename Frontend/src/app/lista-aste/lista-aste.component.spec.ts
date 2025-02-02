import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaAsteComponent } from './lista-aste.component';

describe('ListaAsteComponent', () => {
  let component: ListaAsteComponent;
  let fixture: ComponentFixture<ListaAsteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaAsteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaAsteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
