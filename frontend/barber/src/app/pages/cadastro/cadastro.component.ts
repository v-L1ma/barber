import { Component, OnInit } from '@angular/core';
import { VoltarBtnComponent } from "../../components/voltar-btn/voltar-btn.component";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CadastroService } from '../../services/cadastro/cadastro.service';

@Component({
  selector: 'app-cadastro',
  imports: [VoltarBtnComponent, ReactiveFormsModule],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.scss'
})
export class CadastroComponent implements OnInit{

  signupForm!: FormGroup;

  constructor(private cadastroService: CadastroService){}

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      nome: new FormControl("", [Validators.required]),
      email: new FormControl("", [Validators.required]),
      dataNascimento: new FormControl("2025-04-02", [Validators.required]),
      celular: new FormControl("", [Validators.required, Validators.minLength(11), Validators.maxLength(11)]),
      senha: new FormControl("", [Validators.required, Validators.minLength(5)]),
      confirmarSenha: new FormControl("", [Validators.required, Validators.minLength(5)])
    })
  }

  signUp(){
    this.cadastroService.cadastrar(this.signupForm.value).subscribe({
      next: (response)=>{
        console.log(response)
      },
      error:(error)=>{
        console.log(error)
      }
    })
    console.log(this.signupForm.value)
  }
}
