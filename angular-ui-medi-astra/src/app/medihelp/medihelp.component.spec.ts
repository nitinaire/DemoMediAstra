import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedihelpComponent } from './medihelp.component';

describe('MedihelpComponent', () => {
  let component: MedihelpComponent;
  let fixture: ComponentFixture<MedihelpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedihelpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedihelpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
