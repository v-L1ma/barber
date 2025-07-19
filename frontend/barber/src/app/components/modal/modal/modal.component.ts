import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-modal',
  imports: [],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.scss'
})
export class ModalComponent {
  @Input() mensagem:string = "";
  @Input() titulo:string = "";
  @Input() isOpen: boolean = false;
  @Output() fechar = new EventEmitter<void>();
  @Output() function = new EventEmitter<void>();

  fecharPopup() {
    this.fechar.emit();
  }

  deletar(){
    this.function.emit();
  }
}
