import { Component } from '@angular/core';
import { CardServicosComponent } from "../../components/card-servicos/card-servicos.component";

@Component({
  selector: 'app-lista',
  imports: [CardServicosComponent],
  templateUrl: './lista.component.html',
  styleUrl: './lista.component.scss'
})
export class ListaComponent {

}
