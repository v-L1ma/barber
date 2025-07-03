import { TestBed } from '@angular/core/testing';

import { CarregarTodosAgendamentosService } from './carregar-todos-agendamentos.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('FetchTodosAgendamentosService', () => {
  let service: CarregarTodosAgendamentosService;
  let httpMock: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(CarregarTodosAgendamentosService);
    httpMock = TestBed.inject(HttpTestingController)
  });

  afterEach(()=>{
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch agendamentos and return a message and a list of agendamentos',()=>{

    const expectedResponse = {
      message: "Agendamentos listados com sucesso",
      agendamentos: []
    }
    service.fetch().subscribe(response => expect(response).toEqual(expectedResponse));

    const req = httpMock.expectOne("https://barberbookingapi.onrender.com/agendamento");
    expect(req.request.method).toBe('GET')
    req.flush(expectedResponse);

  });

});
