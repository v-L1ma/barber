import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SucessoAgendamentoComponent } from './sucesso-agendamento.component';

describe('SucessoAgendamentoComponent', () => {
  let component: SucessoAgendamentoComponent;
  let fixture: ComponentFixture<SucessoAgendamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SucessoAgendamentoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SucessoAgendamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
