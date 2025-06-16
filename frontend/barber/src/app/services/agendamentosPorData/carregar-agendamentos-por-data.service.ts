import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TFetchAgendamentosResponse } from '../../types/TFetchAgendamentosResponse';

@Injectable({
  providedIn: 'root'
})
export class CarregarAgendamentosPorDataService {

  private readonly baseUrl = 'https://barberbookingapi.onrender.com';

  constructor(private http: HttpClient) { }

  fetch(data:string):Observable<TFetchAgendamentosResponse>{
    return this.http.get<TFetchAgendamentosResponse>(`${this.baseUrl}/agendamento/${data}`)
  }
}
