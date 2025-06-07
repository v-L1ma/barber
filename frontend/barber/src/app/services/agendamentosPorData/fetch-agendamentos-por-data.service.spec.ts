import { TestBed } from '@angular/core/testing';

import { FetchAgendamentosPorDataService } from './fetch-agendamentos-por-data.service';

describe('FetchAgendamentosPorDataService', () => {
  let service: FetchAgendamentosPorDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FetchAgendamentosPorDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
