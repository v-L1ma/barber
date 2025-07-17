import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from "../../components/header/header.component";
import { TCliente } from '../../types/TCliente';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-perfil',
  imports: [HeaderComponent, RouterModule],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.scss'
})
export class PerfilComponent implements OnInit{

  clienteInfo!: TCliente;

  constructor(private router:Router){}

  ngOnInit(): void {
    const local = localStorage.getItem("clienteInfo")
    this.clienteInfo = JSON.parse(local!);
  }

  

}
