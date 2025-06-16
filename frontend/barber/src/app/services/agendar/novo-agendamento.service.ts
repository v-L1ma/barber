import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TAgendamento } from '../../types/TAgendamentos';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NovoAgendamentoService {

  private readonly baseUrl = 'https://barberbookingapi.onrender.com';

  constructor(private http: HttpClient) { }

  agendar(agendamento: TAgendamento):Observable<any>{
    return this.http.post(`${this.baseUrl}/agendamento`, agendamento, {observe: 'response'})
  }
}
