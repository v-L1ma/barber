import { Component, OnInit } from '@angular/core';
import { VoltarBtnComponent } from "../../components/voltar-btn/voltar-btn.component";
import { Router } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FetchAgendamentosPorDataService } from '../../services/fetch-agendamentos-por-data.service';

type TAgendamento = {
  id:number;
  data: string;
  horario: string;
  cliente: string;
}

@Component({
  selector: 'app-agendamento',
  imports: [VoltarBtnComponent, ReactiveFormsModule],
  templateUrl: './agendamento.component.html',
  styleUrl: './agendamento.component.scss'
})
export class AgendamentoComponent implements OnInit {
  servico: string;
  today: string;
  agendamentoForm!: FormGroup;
  agendamentos: TAgendamento[] = [];

  constructor(private router: Router, private fetchAgendamentosPorDataService: FetchAgendamentosPorDataService){
    const navigation = router.getCurrentNavigation();
    this.servico = navigation?.extras.state?.['servico'];
    console.log(this.servico);

    const data = new Date();
    this.today = data.toJSON().slice(0,10);
    console.log("today"+this.today)
  }
  
  ngOnInit(): void {
    this.agendamentoForm = new FormGroup({
      data: new FormControl<string | null>(this.today, [Validators.required, Validators.nullValidator]),
      horario: new FormControl<string>("", [Validators.required, Validators.minLength(5)]),
      cliente: new FormControl<string>("", [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
      //celular: new FormControl<string>("", [Validators.required, Validators.minLength(11), Validators.maxLength(11)])
    })

    this.fetchAgendamentosPorDia()
  }

  get data(){
    return this.agendamentoForm?.get("data")!;
  }

  get cliente(){
    return this.agendamentoForm?.get("cliente")!;
  }

  get horario(){
    return this.agendamentoForm?.get("horario")!;
  }

  get celular(){
    return this.agendamentoForm?.get("celular")!;
  }

  fetchAgendamentosPorDia(){
    console.log("Data" + this.data.value)
    //falta passar o valor do input date para fazer a 
    this.fetchAgendamentosPorDataService.fetch(this.data.value).subscribe({
      next: (response)=>{
        this.agendamentos = response.agendamentos
        console.log(this.agendamentos)
      },
      error: (error)=>{
        console.log(error)
      }
    })
  }

  validandoHorario(horario:string){
    console.log(this.agendamentos.some(agendamento => agendamento.horario.slice(0,5) == horario));
    return this.agendamentos.some(agendamento => agendamento.horario.slice(0,5) == horario);
  }

  onSubmit(){
    console.log("implementar")
    console.log(this.agendamentoForm.value)
  }

}
