import { Router } from '@angular/router';
import { LoginService } from './../../../services/login.service';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Authentification } from 'src/app/interfaces/authentification';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-buyer-login',
  templateUrl: './buyer-login.component.html',
  styleUrls: ['./buyer-login.component.css']
})
export class BuyerLoginComponent implements OnInit {

  constructor(
    private loginService: LoginService,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  getLastPage(): string|null{
    return localStorage.getItem('lastPage');
  }

  onSubmit(loginForm: NgForm){
    this.loginService.loginUser(loginForm.value).subscribe(
      (response: Authentification)=>{
        localStorage.setItem('token',response.jwtToken);
        this.router.navigate([localStorage.getItem('lastPage')]);

      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
      );

  }
}
