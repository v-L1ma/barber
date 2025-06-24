import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MeusAgendamentosComponent } from './meus-agendamentos.component';
import { CarregarTodosAgendamentosService } from '../../services/agendametosTodos/carregar-todos-agendamentos.service';
import { of } from 'rxjs';
import { DeletarAgendamentoService } from '../../services/deletarAgendamento/deletar-agendamento.service';

const expectedCarregarAgendamentosServiceResponse = {
  message: "Agendamentos listados com sucesso",
  agendamentos: [
    {
        data: "2025-02-13",
        horario: "10:00",
        cliente: "Vanuza",
        servico: "Corte feminino"
    },
    {
        data: "2025-02-13",
        horario: "12:00",
        cliente: "Vinicius",
        servico: "Corte"
    }
  ]
}

fdescribe('MeusAgendamentosComponent', () => {
  let component: MeusAgendamentosComponent;
  let fixture: ComponentFixture<MeusAgendamentosComponent>;
  let carregarAgendamentosServiceMock: jasmine.SpyObj<CarregarTodosAgendamentosService>
  let deletarAgendamentoServiceMock: jasmine.SpyObj<DeletarAgendamentoService>

  beforeEach(async () => {

    carregarAgendamentosServiceMock = jasmine.createSpyObj('carregarAgendamentosService', ['fetch'])
    carregarAgendamentosServiceMock.fetch.and.returnValue(of(expectedCarregarAgendamentosServiceResponse))

    deletarAgendamentoServiceMock = jasmine.createSpyObj('deletarAgendamentosServices', ['delete'])
    deletarAgendamentoServiceMock.delete.and.returnValue(of({ body: { message: 'Agendamento excluido com sucesso' }, status: 200 }))

    await TestBed.configureTestingModule({
      imports: [MeusAgendamentosComponent],
      providers:[
        {
        provide: CarregarTodosAgendamentosService, useValue: carregarAgendamentosServiceMock
        },
        {
        provide: DeletarAgendamentoService, useValue: deletarAgendamentoServiceMock
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MeusAgendamentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
