import { Component, OnInit } from '@angular/core';
import { TAgendamento } from '../../types/TAgendamentos';

import { DeletarAgendamentoService } from '../../services/deletarAgendamento/deletar-agendamento.service';
import { CarregarTodosAgendamentosService } from '../../services/agendametosTodos/carregar-todos-agendamentos.service';
import { Router, RouterModule } from '@angular/router';
import { ListarAgendamentosPorClienteIdService } from '../../services/listarAgendamentosPorClienteId/listar-agendamentos-por-cliente-id.service';
import { ModalComponent } from "../../components/modal/modal/modal.component";
import { HeaderComponent } from '../../components/header/header.component';

@Component({
  selector: 'app-meus-agendamentos',
  imports: [RouterModule, ModalComponent, HeaderComponent],
  templateUrl: './meus-agendamentos.component.html',
  styleUrl: './meus-agendamentos.component.scss'
})
export class MeusAgendamentosComponent implements OnInit{

  agendamentos: TAgendamento[] = [];
  isPopUpOpen:boolean = false;
  
  private clienteInfo = JSON.parse(localStorage.getItem("clienteInfo")!) ?? null;
  private clienteId:number = this.clienteInfo?.id ?? 0;

  constructor(private listarAgendamentosPorClienteIdService: ListarAgendamentosPorClienteIdService,
    private deleteAgendamento: DeletarAgendamentoService,
    private router: Router
  ){}

  setPopUpOpen(){
    this.isPopUpOpen = !this.isPopUpOpen;
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
    // this.carregarAgendamentos(this.clienteId)
    this.agendamentos = [
    {
        data: "2025-02-13",
        horario: "10:00",
        cliente: "Vanuza",
        servico: "Corte feminino"
    },
    {
        data: "2025-02-13",
        horario: "12:00",
        cliente: "Vinicius",
        servico: "Corte"
    },
    {
        data: "2025-02-13",
        horario: "12:00",
        cliente: "Vinicius",
        servico: "Corte"
    }
  ]
  }

  deletar(id:number){
    console.log(id)
    this.deleteAgendamento.delete(id).subscribe({
      next:(response)=>{
        console.log(response)
        this.carregarAgendamentos(this.clienteId)
        this.setPopUpOpen();
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
