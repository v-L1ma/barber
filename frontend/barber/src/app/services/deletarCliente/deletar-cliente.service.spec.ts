import { TestBed } from '@angular/core/testing';

import { DeletarClienteService } from './deletar-cliente.service';

describe('DeletarClienteService', () => {
  let service: DeletarClienteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeletarClienteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
