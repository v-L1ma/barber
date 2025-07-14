import { Component, OnInit } from '@angular/core';
import { VoltarBtnComponent } from "../../components/voltar-btn/voltar-btn.component";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginService } from '../../services/login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [VoltarBtnComponent, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit{

  constructor(private loginService: LoginService, private router:Router){}

  loginForm!: FormGroup;
  
  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl("", Validators.required),
      senha: new FormControl("", [Validators.required, Validators.minLength(5)])
    });
  }

  get email(){
    return this.loginForm.get('email')
  }

   get senha(){
    return this.loginForm.get('senha')
  }

  login(){
    this.loginService.login(this.loginForm.value).subscribe({
      next: (response)=>{
        localStorage.setItem("token", response.token)
        localStorage.setItem("clienteInfo", JSON.stringify(response.clienteInfo))
        this.router.navigateByUrl("servicos");
        console.log(response)
      },
      error:(error)=>{
        console.log(error)
      }
    })
    console.log(this.loginForm.value)
  }

}