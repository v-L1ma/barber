import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TCadastrarClienteResponse } from '../../types/TCadastrarClienteResponse';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DeletarClienteService {

  constructor(private httpClient: HttpClient) { }

  executar(id:number):Observable<TCadastrarClienteResponse>{
    return this.httpClient.delete<TCadastrarClienteResponse>(`${environment.apiUrl}/cliente/${id}`);
  }
}
