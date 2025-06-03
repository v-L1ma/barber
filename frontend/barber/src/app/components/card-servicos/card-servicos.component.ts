import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-card-servicos',
  imports: [RouterModule],
  templateUrl: './card-servicos.component.html',
  styleUrl: './card-servicos.component.scss'
})
export class CardServicosComponent {

  @Input() titulo:string = "";
  @Input() preco:string = "";


//   <div class="card">
//     <div>
//         <h2>Corte + barba</h2>
//         <div class="lista">
//             <img src="homem.png" alt="">
//             <img src="mulher.png" alt="">
//             <img src="homem.png" alt="">
//         </div>
//     </div>
//     <div>
//         <p>R$ 80,00</p>
//         <button [routerLink]="['/agendamento']" [state]="{servico: 'Corte + Barba'}">
//             Reservar
//         </button>
//     </div>
// </div>

}
