import { TestBed } from '@angular/core/testing';

import { CarregarAgendamentosPorDataService } from './carregar-agendamentos-por-data.service';

describe('FetchAgendamentosPorDataService', () => {
  let service: CarregarAgendamentosPorDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CarregarAgendamentosPorDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
