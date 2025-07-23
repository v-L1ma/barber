import { Injectable } from '@angular/core';
import { TCliente } from '../../types/TCliente';
import { TLoginClienteResponse } from '../../types/TLoginCliente';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

   constructor(private httpClient: HttpClient) { }

  login(cliente: TCliente):Observable<TLoginClienteResponse>{
    return this.httpClient.post<TLoginClienteResponse>(`${environment.apiUrl}/cliente/login`, cliente)
  }
}
