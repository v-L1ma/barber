import { TestBed } from '@angular/core/testing';

import { DeletarAgendamentoService } from './deletar-agendamento.service';

describe('DeletarAgendamentoService', () => {
  let service: DeletarAgendamentoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeletarAgendamentoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
