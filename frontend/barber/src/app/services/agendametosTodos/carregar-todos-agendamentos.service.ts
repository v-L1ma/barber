import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TFetchAgendamentosResponse } from '../../types/TFetchAgendamentosResponse';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CarregarTodosAgendamentosService {


  constructor(private http: HttpClient) { }

  fetch():Observable<any>{
    return this.http.get(`${environment.apiUrl}/agendamento`);
  }
}
