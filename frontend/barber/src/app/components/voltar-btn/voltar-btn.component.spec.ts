import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoltarBtnComponent } from './voltar-btn.component';

describe('VoltarBtnComponent', () => {
  let component: VoltarBtnComponent;
  let fixture: ComponentFixture<VoltarBtnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VoltarBtnComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VoltarBtnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
