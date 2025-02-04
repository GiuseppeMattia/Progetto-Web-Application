import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnunciProfiloComponent } from './annunci-profilo.component';

describe('AnnunciProfiloComponent', () => {
  let component: AnnunciProfiloComponent;
  let fixture: ComponentFixture<AnnunciProfiloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnnunciProfiloComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnnunciProfiloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
