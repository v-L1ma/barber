import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from "../../components/header/header.component";
import { TCliente } from '../../types/TCliente';
import { Router, RouterModule } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AtualizarDadosCadastraisService } from '../../services/atualizarDadosCadastrais/atualizar-dados-cadastrais.service';
import { DeletarClienteService } from '../../services/deletarCliente/deletar-cliente.service';
import { ModalComponent } from "../../components/modal/modal/modal.component";

@Component({
  selector: 'app-perfil',
  imports: [HeaderComponent, RouterModule, ReactiveFormsModule, ModalComponent],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.scss'
})
export class PerfilComponent implements OnInit{

  clienteInfo!: TCliente;
  form!: FormGroup;
  isAlterarSenha: boolean = false;
  isPopUpOpen: boolean = false;
  status: string = "";
  responseMessage:string="";

  constructor(private router:Router, 
    private atualizarDadosService: AtualizarDadosCadastraisService,
    private deletarClienteService: DeletarClienteService){}

  ngOnInit(): void {
    const local = localStorage.getItem("clienteInfo")
    this.clienteInfo = JSON.parse(local!);

    this.form = new FormGroup({
      nome: new FormControl(this.clienteInfo.nome, [Validators.required]),
      email: new FormControl(this.clienteInfo.email, [Validators.required]),
      dataNascimento: new FormControl("2004-04-06", [Validators.required]),
      celular: new FormControl(this.clienteInfo.celular, [Validators.required, Validators.minLength(11), Validators.maxLength(11)]),
      senha: new FormControl(null, [Validators.required, Validators.minLength(5)]),
      confirmarSenha: new FormControl(null, [Validators.required, Validators.minLength(5)])
    });

  }

  setIsAlterarSenha(){
    this.isAlterarSenha = !this.isAlterarSenha;
  }

  submit(){
    console.log(this.form.value)
    this.atualizarDadosService.executar(this.clienteInfo.id!, this.form.value).subscribe({
      next:(response)=>{
        console.log(response);
        this.responseMessage = response.message;
        this.status = "sucess";
      },
      error:(error)=>{
        console.log(error.message);
        this.responseMessage = error.error.message;
        this.status = "erro";
      }
    })
  }

  deletar(){
    this.deletarClienteService.executar(this.clienteInfo.id!).subscribe({
      next:(response)=>{
        console.log(response.message);
        localStorage.clear();
        this.router.navigate(['/']);
      },
      error:(error)=>{
        console.log(error);
      }
    })
  }
  
  fecharPopup() {
    this.isPopUpOpen = !this.isPopUpOpen;
  }

  

}
