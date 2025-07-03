import { TestBed } from '@angular/core/testing';

import { CarregarAgendamentosPorDataService } from './carregar-agendamentos-por-data.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('FetchAgendamentosPorDataService', () => {
  let service: CarregarAgendamentosPorDataService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule]
    });
    service = TestBed.inject(CarregarAgendamentosPorDataService);
    httpMock = TestBed.inject(HttpTestingController)
  });

  afterEach(()=>{
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("should fetch agendamentos and return a message and a list of agendamentos", ()=>{

    const expectedResponse = {
      message: "Agendamentos listado com sucesso",
      agendamentos: []
    }

    service.fetch("2025-02-03").subscribe(response => expect(response).toEqual(expectedResponse));

    const req = httpMock.expectOne("https://barberbookingapi.onrender.com/agendamento/2025-02-03");

    expect(req.request.method).toBe("GET");
    req.flush(expectedResponse);

  });
});
