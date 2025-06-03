import { Component } from '@angular/core';
import { VoltarBtnComponent } from "../../components/voltar-btn/voltar-btn.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-agendamento',
  imports: [VoltarBtnComponent],
  templateUrl: './agendamento.component.html',
  styleUrl: './agendamento.component.scss'
})
export class AgendamentoComponent {
  servico: string;

  constructor(private router: Router){
    const navigation = router.getCurrentNavigation();
    this.servico = navigation?.extras.state?.['servico'];
    console.log(this.servico);
  }

}
