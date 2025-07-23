import { TestBed } from '@angular/core/testing';

import { AtualizarDadosCadastraisService } from './atualizar-dados-cadastrais.service';

describe('AtualizarDadosCadastraisService', () => {
  let service: AtualizarDadosCadastraisService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AtualizarDadosCadastraisService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
