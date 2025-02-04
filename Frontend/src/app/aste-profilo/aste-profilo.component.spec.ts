import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsteProfiloComponent } from './aste-profilo.component';

describe('AsteProfiloComponent', () => {
  let component: AsteProfiloComponent;
  let fixture: ComponentFixture<AsteProfiloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AsteProfiloComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AsteProfiloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
