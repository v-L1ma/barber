import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CarregarAgendamentosPorDataService } from '../../services/agendamentosPorData/carregar-agendamentos-por-data.service';
import { TAgendamento } from '../../types/TAgendamentos';
import { NovoAgendamentoService } from '../../services/agendar/novo-agendamento.service';
import { CarregarTodosAgendamentosService } from '../../services/agendametosTodos/carregar-todos-agendamentos.service';
import { EditarAgendamentoService } from '../../services/editarAgendamento/editar-agendamento.service';
import { SucessoAgendamentoComponent } from "../../components/sucesso-agendamento/sucesso-agendamento.component";
import { TCliente } from '../../types/TCliente';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../components/header/header.component';

@Component({
  selector: 'app-agendamento',
  imports: [ReactiveFormsModule, SucessoAgendamentoComponent, CommonModule, HeaderComponent],
  templateUrl: './agendamento.component.html',
  styleUrl: './agendamento.component.scss'
})
export class AgendamentoComponent implements OnInit {
  clienteInfo: TCliente;
  servico: string;
  today: string;
  agendamentoForm!: FormGroup;
  agendamentos: TAgendamento[] = [];

  profissionais = [
    { id: 1, nome: 'Ricardo Silva', cargo: 'Master Barber • 12 anos exp.', imagem: 'https://lh3.googleusercontent.com/aida-public/AB6AXuBJFz6fBJTrFGgpnlZoEtTeb84oGt_FQl_iqNnk2rAckmLvsLdRrdnhwmqbCsvrZO60YDaY8dbkTOQ_jdpxR5AnDWfXvAS-zXYz-VMjFLhGVjbYXKpksCgH5-W2trbat-d-u4NGxkwWyYnpmR1LZWYzy0qWTq37mMoK7cauKNjwPB8WggVoVAwj4Q9jHjy5F1XaEXMqo3q7OXOwrcqumG_xzD5F_gfvZhtfAlFVrxHI-DXoDHihLL0gBxdZrHI4tzxtpjMvriUbjk-t' },
    { id: 2, nome: 'André Santos', cargo: 'Senior Stylist • 8 anos exp.', imagem: 'https://lh3.googleusercontent.com/aida-public/AB6AXuBVl5ALrhDzCA2Dy70qg5ohgtqerGmaBys7EXdRLh1myqoW52DmQV3Jq6FI7j908jEvVXyK_X3ihtlvE_gFIIV4o2ls7F0IoumOk77BIjUe4w2MCGyWauxgQDZTSOQQrWNXLYtUpRsNuPFR7yZmNi_qE51a2j2q_LPtS2-McbaN5Bt0sVVJW2BmuY1zulPu4CsM_K6eD9w1NQaAlYdKUNm9i75HC3CYQLdvfd_Taop3pL3I2gwl9q3pZFT3tay6Im8FKjeN7OBq8uxT' }
  ];

  servicosPopulares = [
    { id: 1, nome: 'Corte masculino', sub: '30 min • Lavagem inclusa', preco: 50.00 },
    { id: 2, nome: 'Corte + Barba', sub: '60 min • Ritual com toalha quente', preco: 90.00 },
    { id: 3, nome: 'Barba', sub: '25 min • Desenho e hidratação', preco: 40.00 }
  ];

  horariosManha = ['09:00', '09:30', '10:00', '10:30', '11:00', '11:30'];
  horariosTarde = ['14:00', '14:30', '15:00', '15:30', '16:00', '16:30'];

  isEditando!:boolean;
  isOpen:boolean = false;
  agendamentoId?: number;
  agendamentoEditar?: TAgendamento;

  status:string = "";
  responseMessage:string = "";

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

    const localClienteInfo = localStorage.getItem("clienteInfo");
    this.clienteInfo = localClienteInfo ? JSON.parse(localClienteInfo) : {};

    const data = new Date();
    this.today = data.toJSON().slice(0,10);
  }
  
  ngOnInit(): void {
    this.agendamentoId = Number(this.route.snapshot.paramMap.get('id'));
    this.isEditando = this.agendamentoId != 0;

    this.agendamentoForm = new FormGroup({
      data: new FormControl<string>(this.today, [Validators.required]),
      horario: new FormControl<string>("", [Validators.required, Validators.minLength(5)]),
      clienteId: new FormControl<number>(this.clienteInfo?.id || 0, [Validators.required]),
      cliente: new FormControl<string>(this.clienteInfo?.nome || "", [Validators.required]),
      celular: new FormControl<string>(this.clienteInfo?.celular || "", [Validators.required]),
      servico: new FormControl<string>(this.servico || 'Corte masculino', [Validators.required]),
      profissional: new FormControl<number>(1, [Validators.required])
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
        if(this.isEditando && this.agendamentos){
          this.agendamentoEditar = this.agendamentos.find((a)=>a.id===this.agendamentoId)
          if (this.agendamentoEditar) {
            this.today = this.agendamentoEditar.data;
            this.agendamentoForm.patchValue({
              data: this.agendamentoEditar.data,
              servico: this.agendamentoEditar.servico,
              cliente: this.agendamentoEditar.cliente,
              horario: this.agendamentoEditar.horario
            });
          }
        } else{
          this.agendamentos = this.agendamentos?.filter((a)=>a.data==this.today) || [];
        }
      },
      error: (error)=>{
        console.log(error)
      }
    })
  }

  onSubmit(){
    if(this.isEditando){
      this.editarAgendamentoService.editar(Number(this.agendamentoId),this.agendamentoForm.value).subscribe({
        next: (response)=>{
          this.status = "sucesso"
          this.responseMessage = "Agendamento atualizado com sucesso"
          this.fecharPopUp()
        },
        error: (error)=>{
          this.status = "erro"
          this.responseMessage = error.error.message
          if(error.status=='0' || error.status=='500'){
            this.responseMessage = "Houve um erro ao se conectar com o servidor."
          }
          this.fecharPopUp()
        }
      })
      this.router.navigate(['servicos/meus-agendamentos'])
      return;
    }

    this.novoAgendamentoService.agendar(this.agendamentoForm.value).subscribe({
      next: (response)=>{
        this.status = "sucesso"
        this.responseMessage = response.body.message
        this.fecharPopUp()
      },
      error: (error)=>{
        this.status = "erro"
        this.responseMessage = error.error.message
        if(error.status=='0' || error.status=='500'){
          this.responseMessage = "Houve um erro ao se conectar com o servidor."
        }
        this.fecharPopUp()
      }
    })
  }

  filtrarAgendamentosPorData() {
    this.carregarAgendamentosPorDataService.fetch(this.data.value).subscribe({
      next: (response)=>{
        this.agendamentos = response.agendamentos
      },
      error: (error)=>{
        console.log(error)
      }
    })
  }
  
  fecharPopUp(){
    this.isOpen = !this.isOpen;
    if(this.status === 'sucesso') {
      this.router.navigate(['servicos/meus-agendamentos']);
    }
  }

  setServico(nome: string) {
    this.agendamentoForm.get('servico')?.setValue(nome);
  }

  setHorario(hr: string) {
    this.agendamentoForm.get('horario')?.setValue(hr);
  }

  getPrecoSelecionado(): number {
    const srv = this.agendamentoForm.get('servico')?.value;
    const s = this.servicosPopulares.find(x => x.nome === srv);
    return s ? s.preco : 0;
  }

  getFormattedDate(): string {
    const d = this.agendamentoForm.get('data')?.value;
    if (!d) return '';
    const [year, month, day] = d.split('-');
    return `${day}/${month}/${year}`;
  }
}
