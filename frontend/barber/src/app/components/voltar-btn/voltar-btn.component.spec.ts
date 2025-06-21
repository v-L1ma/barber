import { ComponentFixture, TestBed } from '@angular/core/testing';
import { VoltarBtnComponent } from './voltar-btn.component';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

describe('VoltarBtnComponent', () => {
  let component: VoltarBtnComponent;
  let fixture: ComponentFixture<VoltarBtnComponent>;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VoltarBtnComponent],
      imports: [RouterTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(VoltarBtnComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate to url received on go', () => {
    component.go = 'agendamento';

    spyOn(router, 'navigate');

    component.navigate();

    expect(router.navigate).toHaveBeenCalledWith(['/agendamento']);
  });
});
