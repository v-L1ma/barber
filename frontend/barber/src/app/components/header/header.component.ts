import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TCliente } from '../../types/TCliente';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  clienteInfo: TCliente = JSON.parse(localStorage.getItem("clienteInfo")!)
  constructor(private router: Router) {}

  logout() {
    localStorage.clear();
    this.router.navigate(['/']);
  }

  navegar(destino: string) {
    this.router.navigate([destino]);
  }

}
