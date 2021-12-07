import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SHAPESComponent } from './shapes.component';

describe('SHAPESComponent', () => {
  let component: SHAPESComponent;
  let fixture: ComponentFixture<SHAPESComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SHAPESComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SHAPESComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
