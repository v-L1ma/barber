import { TestBed } from '@angular/core/testing';

import { NovoAgendamentoService } from './novo-agendamento.service';

describe('NovoAgendamentoService', () => {
  let service: NovoAgendamentoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NovoAgendamentoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
