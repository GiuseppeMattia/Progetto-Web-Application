import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoriaAnnunciComponent } from './categoriaannunci.component';

describe('CategoriaannunciComponent', () => {
  let component: CategoriaAnnunciComponent;
  let fixture: ComponentFixture<CategoriaAnnunciComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoriaAnnunciComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategoriaAnnunciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
