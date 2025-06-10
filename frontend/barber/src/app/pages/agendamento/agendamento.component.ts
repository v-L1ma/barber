import { Component, Input, OnInit } from '@angular/core';
import { VoltarBtnComponent } from "../../components/voltar-btn/voltar-btn.component";
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CarregarAgendamentosPorDataService } from '../../services/agendamentosPorData/carregar-agendamentos-por-data.service';
import { TAgendamento } from '../../types/TAgendamentos';
import { NovoAgendamentoService } from '../../services/agendar/novo-agendamento.service';
import { CarregarTodosAgendamentosService } from '../../services/agendametosTodos/carregar-todos-agendamentos.service';
import { EditarAgendamentoService } from '../../services/editarAgendamento/editar-agendamento.service';
import { SucessoAgendamentoComponent } from "../../components/sucesso-agendamento/sucesso-agendamento.component";

@Component({
  selector: 'app-agendamento',
  imports: [VoltarBtnComponent, ReactiveFormsModule, SucessoAgendamentoComponent],
  templateUrl: './agendamento.component.html',
  styleUrl: './agendamento.component.scss'
})
export class AgendamentoComponent implements OnInit {
  servico: string;
  today: string;
  agendamentoForm!: FormGroup;
  agendamentos: TAgendamento[] = [];

  isEditando!:boolean;
  isOpen:boolean = false;
  agendamentoId?: number;
  agendamentoEditar?: TAgendamento;

  status:string = "";

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private carregarAgendamentosService: CarregarTodosAgendamentosService,
    private carregarAgendamentosPorDataService: CarregarAgendamentosPorDataService,
    private editarAgendamentoService: EditarAgendamentoService,
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
    this.agendamentoId = Number(this.route.snapshot.paramMap.get('id'));
    this.isEditando = this.agendamentoId!=0;

    this.agendamentoForm = new FormGroup({
      data: new FormControl<string>(this.today, [Validators.required, Validators.nullValidator]),
      horario: new FormControl<string>("", [Validators.required, Validators.minLength(5)]),
      cliente: new FormControl<string>("", [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
      servico: new FormControl<string>(this.servico, [Validators.required])
      //celular: new FormControl<string>("", [Validators.required, Validators.minLength(11), Validators.maxLength(11)])
    })

    this.carregarAgendamentos()

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

  get servicoField(){
    return this.agendamentoForm?.get("servico")!;
  }

  carregarAgendamentos(){
    this.carregarAgendamentosService.fetch().subscribe({
      next: (response)=>{
        this.agendamentos = response.agendamentos
        console.log("agendamentos", this.agendamentos)

        if(this.isEditando){
          console.log(this.agendamentos)
          console.log("existeumid")
          console.log("id", this.agendamentoId)

          this.agendamentoEditar = this.agendamentos.find((a)=>a.id===this.agendamentoId)
          this.today = this.agendamentoEditar?.data!;
          this.servicoField.setValue(this.agendamentoEditar?.servico!);
          this.cliente.setValue(this.agendamentoEditar?.cliente);
          console.log("agendamento editar",this.agendamentoEditar);
          
        } else{
          this.agendamentos = this.agendamentos.filter((a)=>a.data==this.today)
          console.log("agendamento alterados",this.agendamentos)
          }

      },
      error: (error)=>{
        console.log(error)
      }
    })
  }

  onSubmit(){

    if(this.isEditando){
      console.log(this.agendamentoForm.value)
      this.editarAgendamentoService.editar(Number(this.agendamentoId),this.agendamentoForm.value).subscribe({
        next: (response)=>{
          this.status = "sucesso"
          this.fecharPopUp()
          console.log(response)
          this.router.navigate(['servicos/meus-agendamentos'])
        },
        error: (error)=>{
          console.log("Status "+error.status)
          console.log("Body "+error.error.message)
          this.status = "erro"
          this.fecharPopUp()
        }
      })
      return;
    }

    this.novoAgendamentoService.agendar(this.agendamentoForm.value).subscribe({
      next: (response)=>{
        this.status = "sucesso"
        console.log("Status "+response.status)
        console.log("Body "+response.body.message)
        console.log(response)
        this.fecharPopUp()
      },
      error: (error)=>{
        this.status = "erro"
        console.log("Status "+error.status)
        console.log("Body "+error.error.message)
        this.fecharPopUp()
      }
    })
  }

  filtrarAgendamentosPorData() {
    this.carregarAgendamentosPorDataService.fetch(this.data.value).subscribe({
      next: (response)=>{
        this.agendamentos = response.agendamentos
        console.log("agendamentos", this.agendamentos)
      },
      error: (error)=>{
        console.log(error)
      }
    })
  
  
  }

  fecharPopUp(){
    this.isOpen = !this.isOpen;
  }

}
