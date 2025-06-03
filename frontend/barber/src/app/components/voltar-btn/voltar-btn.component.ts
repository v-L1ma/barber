import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-voltar-btn',
  imports: [RouterModule],
  templateUrl: './voltar-btn.component.html',
  styleUrl: './voltar-btn.component.scss'
})
export class VoltarBtnComponent {

  @Input() go : string="/";


}
