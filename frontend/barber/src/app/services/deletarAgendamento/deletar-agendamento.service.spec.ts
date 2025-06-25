import { TestBed } from '@angular/core/testing';

import { DeletarAgendamentoService } from './deletar-agendamento.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('DeletarAgendamentoService', () => {
  let service: DeletarAgendamentoService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(DeletarAgendamentoService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(()=>{
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("should delete an agendamento and return a message", ()=>{

    const expectedResponse = {
      message: "Agendamento excluido com sucesso!"
    }

    service.delete(1).subscribe(response=> expect(response).toBe(expectedResponse));

    const req = httpMock.expectOne("https://barberbookingapi.onrender.com/agendamento/1");
    expect(req.request.method).toBe('DELETE')

    req.flush(expectedResponse);

  });
});
