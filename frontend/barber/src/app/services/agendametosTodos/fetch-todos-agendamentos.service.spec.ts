import { TestBed } from '@angular/core/testing';

import { FetchTodosAgendamentosService } from './fetch-todos-agendamentos.service';

describe('FetchTodosAgendamentosService', () => {
  let service: FetchTodosAgendamentosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FetchTodosAgendamentosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
