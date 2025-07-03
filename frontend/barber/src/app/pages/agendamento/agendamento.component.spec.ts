import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendamentoComponent } from './agendamento.component';
import { ActivatedRoute, Router } from '@angular/router';
import { CarregarTodosAgendamentosService } from '../../services/agendametosTodos/carregar-todos-agendamentos.service';
import { of } from 'rxjs';
import { CarregarAgendamentosPorDataService } from '../../services/agendamentosPorData/carregar-agendamentos-por-data.service';
import { NovoAgendamentoService } from '../../services/agendar/novo-agendamento.service';
import { EditarAgendamentoService } from '../../services/editarAgendamento/editar-agendamento.service';

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

describe('AgendamentoComponent', () => {
  let component: AgendamentoComponent;
  let fixture: ComponentFixture<AgendamentoComponent>;
  let carregarAgendamentosServiceMock: jasmine.SpyObj<CarregarTodosAgendamentosService>
  let carregarAgendamentosPorDataServiceMock: jasmine.SpyObj<CarregarAgendamentosPorDataService>
  let novoAgendamentoServiceMock: jasmine.SpyObj<NovoAgendamentoService>
  let editarAgendamentoServiceMock: jasmine.SpyObj<EditarAgendamentoService>

  beforeEach(async () => {

    carregarAgendamentosServiceMock = jasmine.createSpyObj('carregarAgendamentosService', ['fetch']);
    carregarAgendamentosServiceMock.fetch.and.returnValue(of(expectedCarregarAgendamentosServiceResponse));

    carregarAgendamentosPorDataServiceMock = jasmine.createSpyObj('carregarAgendamentosPorDataService', ['fetch']);
    carregarAgendamentosPorDataServiceMock.fetch.and.returnValue(of(expectedCarregarAgendamentosServiceResponse));

    novoAgendamentoServiceMock = jasmine.createSpyObj('novoAgendamentoService', ['agendar']);
    novoAgendamentoServiceMock.agendar.and.returnValue(of({ body: { message: 'Agendamento feito com sucesso' }, status: 201 }));

    editarAgendamentoServiceMock = jasmine.createSpyObj('editarAgendamentoService', ['editar']);
    editarAgendamentoServiceMock.editar.and.returnValue(of({ body: { message: 'Agendamento atualizado com sucesso' }, status: 200 }));

    await TestBed.configureTestingModule({
      imports: [AgendamentoComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: (key: string) => '0'
              }
            }
          }
        },
        {
          provide: CarregarTodosAgendamentosService, useValue: carregarAgendamentosServiceMock
        },
        {
          provide: CarregarAgendamentosPorDataService, useValue: carregarAgendamentosPorDataServiceMock
        },
        {
          provide: NovoAgendamentoService, useValue: novoAgendamentoServiceMock
        },
        {
          provide: EditarAgendamentoService, useValue: editarAgendamentoServiceMock
        }
      ]
    })
    .compileComponents();

    carregarAgendamentosServiceMock = TestBed.inject(CarregarTodosAgendamentosService) as jasmine.SpyObj<CarregarTodosAgendamentosService>
    fixture = TestBed.createComponent(AgendamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call carregar agendamentos on init', ()=>{
    expect(carregarAgendamentosServiceMock.fetch).toHaveBeenCalled();
  });

  it("should call filtrarAgendamentosPorData when date change", ()=>{
    component.data.setValue('2025-06-21');
    component.filtrarAgendamentosPorData();
    expect(carregarAgendamentosPorDataServiceMock.fetch).toHaveBeenCalledWith('2025-06-21');
  });

  it("should call novoAgendamentoService on submit when not editing", ()=>{
    component.isEditando = false;
    component.agendamentoId = 0;

    component.agendamentoForm.setValue({
      data: '2025-06-21',
      horario: '10:00',
      cliente: 'Vinicius',
      servico: 'Corte'
    });
    component.onSubmit();

    expect(novoAgendamentoServiceMock.agendar).toHaveBeenCalled();
  });

  it("should call editarAgendamentoService on submit when editing", ()=>{
    component.isEditando = true;
    component.agendamentoId = 1;

    component.agendamentoForm.setValue({
      data: '2025-06-21',
      horario: '10:00',
      cliente: 'Vinicius',
      servico: 'Corte'
    });

    component.onSubmit();

    expect(editarAgendamentoServiceMock.editar).toHaveBeenCalled();
  });
});
