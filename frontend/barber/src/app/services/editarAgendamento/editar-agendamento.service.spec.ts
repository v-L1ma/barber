import { TestBed } from '@angular/core/testing';

import { EditarAgendamentoService } from './editar-agendamento.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HtmlParser } from '@angular/compiler';

describe('EditarAgendamentoService', () => {
  let service: EditarAgendamentoService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(EditarAgendamentoService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("should delete an agendamento and return a message", ()=>{

    const expectedResponse = {
      message: "Agendamento atualizado com sucesso!"
    }

    const agendamento = {
        data: "2025-02-13",
        horario: "12:00",
        cliente: "Vinicius",
        servico: "Corte"
    }

    service.editar(1, agendamento).subscribe(response=> expect(response).toBe(expectedResponse));

    const req = httpMock.expectOne("https://barberbookingapi.onrender.com/agendamento/1");
    expect(req.request.method).toBe('PUT');

    req.flush(expectedResponse);

  });
});
