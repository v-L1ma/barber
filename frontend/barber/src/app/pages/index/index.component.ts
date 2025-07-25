import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { LoadingComponent } from "../../components/loading/loading/loading.component";

@Component({
  selector: 'app-index',
  imports: [RouterModule, CommonModule, LoadingComponent],
  templateUrl: './index.component.html',
  styleUrl: './index.component.scss'
})
export class IndexComponent {

  isOpen:boolean = false;
  button:string = "";
  isLoading:boolean = false;

  openMenu(){
    this.isOpen = !this.isOpen;
  }

  setIsloading(button:string){
    this.isLoading = !this.isLoading;
    this.button = button;
  }

}
