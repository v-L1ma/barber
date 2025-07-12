import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TCliente } from '../../types/TCliente';
import { Observable } from 'rxjs';
import { TCadastrarClienteResponse } from '../../types/TCadastrarClienteResponse';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CadastroService {

  constructor(private httpClient: HttpClient) { }

  cadastrar(cliente: TCliente):Observable<TCadastrarClienteResponse>{
    return this.httpClient.post<TCadastrarClienteResponse>(`${environment.apiUrl}/auth/cadastro`, cliente)
  }
}
