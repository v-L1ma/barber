import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ListarAgendamentosPorClienteIdService } from '../listarAgendamentosPorClienteId/listar-agendamentos-por-cliente-id.service';

describe('FetchTodosAgendamentosService', () => {
  let service: ListarAgendamentosPorClienteIdService;
  let httpMock: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(ListarAgendamentosPorClienteIdService);
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
    const id:number = 1;
    service.fetch(id).subscribe(response => expect(response).toEqual(expectedResponse));

    const req = httpMock.expectOne(`https://barberbookingapi.onrender.com/agendamento/${id}`);
    expect(req.request.method).toBe('GET')
    req.flush(expectedResponse);

  });

});
