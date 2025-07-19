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
  status: string="";
  responseMessage: string="";

  constructor(private cadastroService: CadastroService){}

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      nome: new FormControl("", [Validators.required, Validators.minLength(5), Validators.maxLength(50)]),
      email: new FormControl("", [Validators.required]),
      dataNascimento: new FormControl("2025-04-02", [Validators.required]),
      celular: new FormControl("", [Validators.required, Validators.minLength(11), Validators.maxLength(11)]),
      senha: new FormControl("", [Validators.required, Validators.minLength(5)]),
      confirmarSenha: new FormControl("", [Validators.required, Validators.minLength(5)])
    })
  }

   get nome(){
    return this.signupForm.get('nome')
  }

   get senha(){
    return this.signupForm.get('senha')
  }

   get celular(){
    return this.signupForm.get('celular')
  }


   get email(){
    return this.signupForm.get('email')
  }

   get confirmarSenha(){
    return this.signupForm.get('confirmarSenha')
  }

  get dataNascimento(){
    return this.signupForm.get('dataNascimento')
  }

  signUp(){

    if(!this.signupForm.invalid){
      this.cadastroService.cadastrar(this.signupForm.value).subscribe({
        next: (response)=>{
          console.log(response)
          this.status="sucess";
          this.responseMessage=response.message;
        },
        error:(error)=>{
          console.log(error)
          this.status="erro";
          this.responseMessage=error.error.message;
        }
      })
      console.log(this.signupForm.value)
      }

    }
}
