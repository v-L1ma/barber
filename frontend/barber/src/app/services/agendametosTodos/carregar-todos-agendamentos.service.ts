import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TFetchAgendamentosResponse } from '../../types/TFetchAgendamentosResponse';

@Injectable({
  providedIn: 'root'
})
export class CarregarTodosAgendamentosService {

  private baseUrl:string = "https://barberbookingapi.onrender.com"


  constructor(private http: HttpClient) { }

  fetch():Observable<any>{
    return this.http.get(`${this.baseUrl}/agendamento`);
  }
}
