import { TestBed } from '@angular/core/testing';

import { NovoAgendamentoService } from './novo-agendamento.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('NovoAgendamentoService', () => {
  let service: NovoAgendamentoService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(NovoAgendamentoService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should create a new agendamento and return a message',()=>{

    const expectedResponse = {
      message: "Agendamento feito com sucesso"
    }

    const agendamento = {
        data: "2025-02-13",
        horario: "12:00",
        cliente: "Vinicius",
        servico: "Corte"
    }

    service.agendar(agendamento).subscribe();

    const req = httpMock.expectOne("https://barberbookingapi.onrender.com/agendamento");
    expect(req.request.method).toBe('POST');

    req.flush(expectedResponse);

  });

});
