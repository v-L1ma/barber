import { Component, OnInit } from '@angular/core';
import { VoltarBtnComponent } from "../../components/voltar-btn/voltar-btn.component";
import { CarregarAgendamentosPorDataService } from '../../services/agendamentosPorData/carregar-agendamentos-por-data.service';
import { TAgendamento } from '../../types/TAgendamentos';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-agenda',
  imports: [VoltarBtnComponent, FormsModule],
  templateUrl: './agenda.component.html',
  styleUrl: './agenda.component.scss'
})
export class AgendaComponent implements OnInit {

  today: string;
  agendamentos: TAgendamento[] = [];
  DataSelected : string;

  constructor(private carregarAgendamentos: CarregarAgendamentosPorDataService){
    const date = new Date();    
    this.today = date.toJSON().slice(0,10);
    this.DataSelected = this.today;
  }
  
  fetch(data:string){
    this.carregarAgendamentos.fetch(data).subscribe({
      next: (response) => {
        this.agendamentos = response.agendamentos.sort(
          (a,b) => parseInt(a.horario.slice(0,2))-parseInt(b.horario.slice(0,2)))
        console.log(response.agendamentos)
      },
      error: (error) => {
        console.log(error.error.message)
        this.agendamentos = error.agendamentos;
      }
    })
  }

  ngOnInit(): void {
    this.fetch(this.today);
  }

}
