import { TestBed } from '@angular/core/testing';

import { CarregarTodosAgendamentosService } from './carregar-todos-agendamentos.service';

describe('FetchTodosAgendamentosService', () => {
  let service: CarregarTodosAgendamentosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CarregarTodosAgendamentosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
