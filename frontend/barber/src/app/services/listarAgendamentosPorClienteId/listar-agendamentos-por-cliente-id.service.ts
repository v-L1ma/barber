import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ListarAgendamentosPorClienteIdService {

  constructor(private http: HttpClient) { }

  fetch(id:number):Observable<any>{
    return this.http.get(`${environment.apiUrl}/agendamento/cliente/${id}`);
  }
}
