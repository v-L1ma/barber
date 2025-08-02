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
  destino!: string;
  status: string="";
  responseMessage: string="";
  
  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl("", [Validators.required, Validators.email]),
      senha: new FormControl("", [Validators.required, Validators.minLength(5)])
    });

    const navigation = this.router.getCurrentNavigation();
    console.log('[LoginComponent] getCurrentNavigation:', navigation);

    console.log('[LoginComponent] history.state:', history.state);

    this.destino = navigation?.extras.state?.['destino']
              ?? history.state?.['destino']
  }

  get email(){
    return this.loginForm.get('email')
  }

   get senha(){
    return this.loginForm.get('senha')
  }

  login(){
    if(!this.loginForm.invalid){
      this.loginService.login(this.loginForm.value).subscribe({
      next: (response)=>{
        this.status="sucess";
        this.responseMessage=response.message;
        localStorage.setItem("token", response.token)
        localStorage.setItem("clienteInfo", JSON.stringify(response.clienteInfo))
        console.log(response)
        console.log("destino no login: ", this.destino)
        this.router.navigateByUrl(this.destino);
      },
      error:(error)=>{
        this.status="erro";
        this.responseMessage=error.error.message;
        console.log(error)

      }
    })
    console.log(this.loginForm.value)
  }
    }

    

}
