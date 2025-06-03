import { Component } from '@angular/core';
import { VoltarBtnComponent } from "../../components/voltar-btn/voltar-btn.component";
import { RouterModule } from '@angular/router';
import { CardServicosComponent } from "../../components/card-servicos/card-servicos.component";

@Component({
  selector: 'app-servicos',
  imports: [VoltarBtnComponent, RouterModule, CardServicosComponent],
  templateUrl: './servicos.component.html',
  styleUrl: './servicos.component.scss'
})
export class ServicosComponent {

}
