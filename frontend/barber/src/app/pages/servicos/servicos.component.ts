import { Component } from '@angular/core';
import { VoltarBtnComponent } from "../../components/voltar-btn/voltar-btn.component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-servicos',
  imports: [VoltarBtnComponent, RouterModule],
  templateUrl: './servicos.component.html',
  styleUrl: './servicos.component.scss'
})
export class ServicosComponent {

}
