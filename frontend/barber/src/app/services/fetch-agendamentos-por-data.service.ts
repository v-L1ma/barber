import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FetchAgendamentosPorDataService {

  constructor(private http: HttpClient) { }

  fetch(data:string):Observable<any>{
    return this.http.get("http://localhost:8080/agendamento/"+data)
  }
}
