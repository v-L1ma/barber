import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-sucesso-agendamento',
  imports: [],
  templateUrl: './sucesso-agendamento.component.html',
  styleUrl: './sucesso-agendamento.component.scss'
})
export class SucessoAgendamentoComponent  {

  @Input() isOpen! :boolean;
  @Output() close = new EventEmitter<void>();
  @Input() status!: string;
  @Input() responseMessage!: string;

  fecharPopUp(){
    this.close.emit();
  }

 


}
