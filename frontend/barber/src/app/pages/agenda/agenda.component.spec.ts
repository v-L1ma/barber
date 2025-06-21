import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendaComponent } from './agenda.component';
import { CarregarAgendamentosPorDataService } from '../../services/agendamentosPorData/carregar-agendamentos-por-data.service';
import { of } from 'rxjs';
import { By } from '@angular/platform-browser';
import { CommonModule} from '@angular/common';

const expectedServiceResponse = {
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

describe('AgendaComponent', () => {
  let component: AgendaComponent;
  let fixture: ComponentFixture<AgendaComponent>;
  let carregarAgendamentosMock: jasmine.SpyObj<CarregarAgendamentosPorDataService>

  beforeEach(async () => {
    carregarAgendamentosMock = jasmine.createSpyObj('carregarAgendamentos', ['fetch']);
    carregarAgendamentosMock.fetch.and.returnValue(of(expectedServiceResponse ))

    await TestBed.configureTestingModule({
      imports: [AgendaComponent, CommonModule],
      providers: [{ provide: CarregarAgendamentosPorDataService, useValue: carregarAgendamentosMock}]
    })
    .compileComponents();

    carregarAgendamentosMock = TestBed.inject(CarregarAgendamentosPorDataService) as jasmine.SpyObj<CarregarAgendamentosPorDataService>;
    fixture = TestBed.createComponent(AgendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it("should call api on init", ()=>{
    fixture.detectChanges();

    expect(carregarAgendamentosMock.fetch).toHaveBeenCalled()
  });
  
  it("should render agendamentos from api", () => {
  fixture.detectChanges();

  const pElements = fixture.debugElement.queryAll(By.css(".card p"));

  const textosEsperados = expectedServiceResponse.agendamentos.flatMap(a => [a.cliente, a.servico]);

  pElements.forEach(element => {
    const text = (element.nativeElement as HTMLElement).textContent!.trim();
    const match = textosEsperados.includes(text);
    expect(match).toBeTrue(); 
  });
});


});
