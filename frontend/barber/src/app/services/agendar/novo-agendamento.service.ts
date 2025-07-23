import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TAgendamento } from '../../types/TAgendamentos';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NovoAgendamentoService {

  constructor(private http: HttpClient) { }

  agendar(agendamento: TAgendamento):Observable<any>{
    return this.http.post(`${environment.apiUrl}/agendamento`, agendamento, {observe: 'response'})
  }
}
