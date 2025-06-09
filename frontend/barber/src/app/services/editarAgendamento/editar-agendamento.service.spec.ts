import { TestBed } from '@angular/core/testing';

import { EditarAgendamentoService } from './editar-agendamento.service';

describe('EditarAgendamentoService', () => {
  let service: EditarAgendamentoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditarAgendamentoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
