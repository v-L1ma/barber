import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TAgendamento } from '../../types/TAgendamentos';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EditarAgendamentoService {

  constructor(private http: HttpClient) { }

  editar(id:number, agendamento:TAgendamento):Observable<any>{
    return this.http.put(`${environment.apiUrl}/agendamento/${id}`, agendamento)
  }
}
