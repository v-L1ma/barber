import { Component, OnInit } from '@angular/core';
import { TAgendamento } from '../../types/TAgendamentos';
import { FetchTodosAgendamentosService } from '../../services/agendametosTodos/fetch-todos-agendamentos.service';
import { DeletarAgendamentoService } from '../../services/deletarAgendamento/deletar-agendamento.service';

@Component({
  selector: 'app-meus-agendamentos',
  imports: [],
  templateUrl: './meus-agendamentos.component.html',
  styleUrl: './meus-agendamentos.component.scss'
})
export class MeusAgendamentosComponent implements OnInit{

  agendamentos: TAgendamento[] = [];

  constructor(private fetchAgendamentos: FetchTodosAgendamentosService,
    private deleteAgendamento: DeletarAgendamentoService
  ){}

  fetch(){
    this.fetchAgendamentos.fetch().subscribe({
      next: (response)=>{
        console.log(response)
        this.agendamentos=response.agendamentos
      },
      error: (error)=>{
        console.log("Status "+error.status)
        console.log("Body "+error.error.message)
      }
    })
  }

  ngOnInit(): void {
    this.fetch()
  }

  deletar(id:number){
    console.log(id)
    this.deleteAgendamento.delete(id).subscribe({
      next:(response)=>{
        console.log(response)
        this.fetch()
      },
      error:(error)=>{
        console.log(error)
      }
    })
  }

}
