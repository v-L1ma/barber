import { Component, OnInit } from '@angular/core';
import { VoltarBtnComponent } from "../../components/voltar-btn/voltar-btn.component";
import { Router } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

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

  constructor(private router: Router){
    const navigation = router.getCurrentNavigation();
    this.servico = navigation?.extras.state?.['servico'];
    console.log(this.servico);

    const data = new Date();
    this.today = data.toJSON().slice(0,10);
    console.log(this.today)
  
    this.fetchAgendamentosPorDia()
  }
  
  ngOnInit(): void {
    this.agendamentoForm = new FormGroup({
      data: new FormControl<Date | null>(null, [Validators.required, Validators.nullValidator]),
      horario: new FormControl<string>("", [Validators.required, Validators.minLength(5)]),
      cliente: new FormControl<string>("", [Validators.required, Validators.minLength(3), Validators.maxLength(50)])
    })  
  }

  fetchAgendamentosPorDia(){
    console.log(this.agendamentoForm?.get("data")?.value)
    //falta passar o valor do input date para fazer a requisição
    
  }

  onSubmit(){
    console.log("implementar")
    console.log(this.agendamentoForm.value)
  }

}
