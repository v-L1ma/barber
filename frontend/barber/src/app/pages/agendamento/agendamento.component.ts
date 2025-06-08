import { Component, Input, OnInit } from '@angular/core';
import { VoltarBtnComponent } from "../../components/voltar-btn/voltar-btn.component";
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CarregarAgendamentosPorDataService } from '../../services/agendamentosPorData/carregar-agendamentos-por-data.service';
import { TAgendamento } from '../../types/TAgendamentos';
import { NovoAgendamentoService } from '../../services/agendar/novo-agendamento.service';

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

  agendamentoId?: number;
  agendamentoEditar?: TAgendamento;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private carregarAgendamentosPorDataService: CarregarAgendamentosPorDataService,
    private novoAgendamentoService: NovoAgendamentoService
  ){
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
      servico: new FormControl<string>(this.servico, [Validators.required])
      //celular: new FormControl<string>("", [Validators.required, Validators.minLength(11), Validators.maxLength(11)])
    })

    this.carregarAgendamentosPorDia(this.data.value)
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

  carregarAgendamentosPorDia(data:string){
    console.log("Data" + data)
    this.carregarAgendamentosPorDataService.fetch(data).subscribe({
      next: (response)=>{
        this.agendamentos = response.agendamentos
        console.log(this.agendamentos)

        this.agendamentoId = Number(this.route.snapshot.paramMap.get('id'));

        if(this.agendamentoId!=0){
          console.log(this.agendamentos)
          console.log("existeumid")
          console.log("id", this.agendamentoId)

          this.agendamentoEditar = this.agendamentos.find((a)=>a.id===this.agendamentoId)
          console.log("agendamento editar",this.agendamentoEditar)
        }

      },
      error: (error)=>{
        console.log(error)
      }
    })
  }

  onSubmit(){
    this.novoAgendamentoService.agendar(this.agendamentoForm.value).subscribe({
      next: (response)=>{
        console.log("Status "+response.status)
        console.log("Body "+response.body.message)
        console.log(response)
      },
      error: (error)=>{
      
        console.log("Status "+error.status)
        console.log("Body "+error.error.message)
      }
    })
  }

  carregarInformacoesDoAgendamento(){
    
  }

}
