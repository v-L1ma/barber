import { Component, OnInit } from '@angular/core';
import { TAgendamento } from '../../types/TAgendamentos';

import { DeletarAgendamentoService } from '../../services/deletarAgendamento/deletar-agendamento.service';
import { CarregarTodosAgendamentosService } from '../../services/agendametosTodos/carregar-todos-agendamentos.service';
import { Router, RouterModule } from '@angular/router';
import { ListarAgendamentosPorClienteIdService } from '../../services/listarAgendamentosPorClienteId/listar-agendamentos-por-cliente-id.service';

@Component({
  selector: 'app-meus-agendamentos',
  imports: [RouterModule],
  templateUrl: './meus-agendamentos.component.html',
  styleUrl: './meus-agendamentos.component.scss'
})
export class MeusAgendamentosComponent implements OnInit{

  agendamentos: TAgendamento[] = [];
  isOpen:boolean = false;
  
  private clienteInfo = JSON.parse(localStorage.getItem("clienteInfo")!);
  private clienteId:number = this.clienteInfo.id;

  constructor(private listarAgendamentosPorClienteIdService: ListarAgendamentosPorClienteIdService,
    private deleteAgendamento: DeletarAgendamentoService,
    private router: Router
  ){}

  setMenuOpen(){
    this.isOpen = !this.isOpen;
  }

  carregarAgendamentos(id:number){
    this.listarAgendamentosPorClienteIdService.fetch(id).subscribe({
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
    this.carregarAgendamentos(this.clienteId)
  }

  deletar(id:number){
    console.log(id)
    this.deleteAgendamento.delete(id).subscribe({
      next:(response)=>{
        console.log(response)
        this.carregarAgendamentos(this.clienteId)
      },
      error:(error)=>{
        console.log(error)
      }
    })
  }


navegarParaEdicao(id: number) {
  this.router.navigate(['/agendamento/editar', id]);
}


}
