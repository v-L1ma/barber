import { Component, OnInit } from '@angular/core';
import { VoltarBtnComponent } from "../../components/voltar-btn/voltar-btn.component";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-cadastro',
  imports: [VoltarBtnComponent, ReactiveFormsModule],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.scss'
})
export class CadastroComponent implements OnInit{

  signupForm!: FormGroup;

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      nome: new FormControl("", [Validators.required]),
      email: new FormControl("", [Validators.required]),
      celular: new FormControl("", [Validators.required, Validators.minLength(11), Validators.maxLength(11)]),
      senha: new FormControl("", [Validators.required, Validators.minLength(5)]),
      confirmarSenha: new FormControl("", [Validators.required, Validators.minLength(5)])
    })
  }

  signUp(){
    console.log(this.signupForm.value)
  }
}
