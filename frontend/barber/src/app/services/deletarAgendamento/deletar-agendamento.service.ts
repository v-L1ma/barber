import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeletarAgendamentoService {

  private baseUrl = "http://localhost:8080"

  constructor(private readonly http: HttpClient) { }

  delete(id:number):Observable<any>{
    return this.http.delete(`${this.baseUrl}/agendamento/${id}`)
  }
}
