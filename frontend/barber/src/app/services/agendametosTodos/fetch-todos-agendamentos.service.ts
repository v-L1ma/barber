import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TFetchAgendamentosResponse } from '../../types/TFetchAgendamentosResponse';

@Injectable({
  providedIn: 'root'
})
export class FetchTodosAgendamentosService {

  private baseUrl:string = "http://localhost:8080"

  constructor(private http: HttpClient) { }

  fetch():Observable<any>{
    return this.http.get(`${this.baseUrl}/agendamento`);
  }
}
