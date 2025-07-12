import { Component, OnInit } from '@angular/core';
import { VoltarBtnComponent } from "../../components/voltar-btn/voltar-btn.component";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [VoltarBtnComponent, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit{

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
    console.log(this.loginForm.value)
  }

}