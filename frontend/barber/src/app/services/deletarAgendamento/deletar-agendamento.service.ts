import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DeletarAgendamentoService {

  constructor(private readonly http: HttpClient) { }

  delete(id:number):Observable<any>{
    return this.http.delete(`${environment.apiUrl}/agendamento/${id}`)
  }
}
