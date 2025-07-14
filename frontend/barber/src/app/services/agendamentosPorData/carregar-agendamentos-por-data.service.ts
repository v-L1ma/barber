import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TFetchAgendamentosResponse } from '../../types/TFetchAgendamentosResponse';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CarregarAgendamentosPorDataService {

  constructor(private http: HttpClient) { }

  fetch(data:string):Observable<TFetchAgendamentosResponse>{
    return this.http.get<TFetchAgendamentosResponse>(`${environment.apiUrl}/agendamento/${data}`)
  }
}
