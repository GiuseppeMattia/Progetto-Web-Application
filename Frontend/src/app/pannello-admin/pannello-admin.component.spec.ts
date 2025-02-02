import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PannelloAdminComponent } from './pannello-admin.component';

describe('PannelloAdminComponent', () => {
  let component: PannelloAdminComponent;
  let fixture: ComponentFixture<PannelloAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PannelloAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PannelloAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
