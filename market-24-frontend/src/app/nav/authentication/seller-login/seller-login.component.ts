import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Authentification } from 'src/app/interfaces/authentification';
import { SellerAuthenticationService } from 'src/app/services/seller-authentication.service';

@Component({
  selector: 'app-seller-login',
  templateUrl: './seller-login.component.html',
  styleUrls: ['./seller-login.component.css']
})
export class SellerLoginComponent implements OnInit {

  constructor(
    private loginService: SellerAuthenticationService,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }
  getLastPage(): string|null{
    return localStorage.getItem('lastPage');
  }

  onSubmit(loginForm: NgForm){
    this.loginService.login(loginForm.value).subscribe(
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
