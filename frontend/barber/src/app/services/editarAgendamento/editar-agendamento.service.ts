import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TAgendamento } from '../../types/TAgendamentos';

@Injectable({
  providedIn: 'root'
})
export class EditarAgendamentoService {

  private baseUrl:string = "https://barberbookingapi.onrender.com"

  constructor(private http: HttpClient) { }

  editar(id:number, agendamento:TAgendamento):Observable<any>{
    return this.http.put(`${this.baseUrl}/agendamento/${id}`, agendamento)
  }
}
