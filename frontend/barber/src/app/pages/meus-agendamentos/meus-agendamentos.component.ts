import { Component, OnInit } from '@angular/core';
import { TAgendamento } from '../../types/TAgendamentos';

import { DeletarAgendamentoService } from '../../services/deletarAgendamento/deletar-agendamento.service';
import { CarregarTodosAgendamentosService } from '../../services/agendametosTodos/carregar-todos-agendamentos.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-meus-agendamentos',
  imports: [RouterModule],
  templateUrl: './meus-agendamentos.component.html',
  styleUrl: './meus-agendamentos.component.scss'
})
export class MeusAgendamentosComponent implements OnInit{

  agendamentos: TAgendamento[] = [];
  isOpen:boolean = false;

  constructor(private carregarAgendamentosService: CarregarTodosAgendamentosService,
    private deleteAgendamento: DeletarAgendamentoService,
    private router: Router
  ){}

  setMenuOpen(){
    this.isOpen = !this.isOpen;
  }

  carregarAgendamentos(){
    this.carregarAgendamentosService.fetch().subscribe({
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
    this.carregarAgendamentos()
  }

  deletar(id:number){
    console.log(id)
    this.deleteAgendamento.delete(id).subscribe({
      next:(response)=>{
        console.log(response)
        this.carregarAgendamentos()
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
