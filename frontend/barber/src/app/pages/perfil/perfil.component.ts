import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from "../../components/header/header.component";
import { TCliente } from '../../types/TCliente';
import { Router, RouterModule } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-perfil',
  imports: [HeaderComponent, RouterModule, ReactiveFormsModule],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.scss'
})
export class PerfilComponent implements OnInit{

  clienteInfo!: TCliente;
  form!: FormGroup;

  constructor(private router:Router){}

  ngOnInit(): void {
    const local = localStorage.getItem("clienteInfo")
    this.clienteInfo = JSON.parse(local!);

    this.form = new FormGroup({
      nome: new FormControl("", [Validators.required]),
      email: new FormControl("", [Validators.required]),
      dataNascimento: new FormControl("2025-04-02", [Validators.required]),
      celular: new FormControl("", [Validators.required, Validators.minLength(11), Validators.maxLength(11)]),
      senha: new FormControl("", [Validators.required, Validators.minLength(5)]),
      confirmarSenha: new FormControl("", [Validators.required, Validators.minLength(5)])
    });

  }

  submit(){
    console.log(this.form.value)
  }

  

}
