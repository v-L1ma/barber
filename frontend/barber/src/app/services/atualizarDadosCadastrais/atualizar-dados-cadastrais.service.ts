import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TCliente } from '../../types/TCliente';
import { Observable } from 'rxjs';
import { TCadastrarClienteResponse } from '../../types/TCadastrarClienteResponse';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AtualizarDadosCadastraisService {

  constructor(private httpClient:HttpClient) { }

  executar(id:number,cliente: TCliente):Observable<TCadastrarClienteResponse>{
    return this.httpClient.put<TCadastrarClienteResponse>(`${environment.apiUrl}/cliente/atualizar-dados/${id}`, cliente);

  }
}
