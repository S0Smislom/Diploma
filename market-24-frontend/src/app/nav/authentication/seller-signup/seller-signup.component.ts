import { SellerAuthenticationService } from './../../../services/seller-authentication.service';
import { Router } from '@angular/router';
import { LoginService } from './../../../services/login.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, NgForm } from '@angular/forms';

@Component({
  selector: 'app-seller-signup',
  templateUrl: './seller-signup.component.html',
  styleUrls: ['./seller-signup.component.css']
})
export class SellerSignupComponent implements OnInit {
  logginService: any;
  name = new FormControl();

  constructor(private loginService: SellerAuthenticationService,
    private router: Router) { }

  ngOnInit(): void {
  }
  signup(signinForm:NgForm){
    document.getElementById('signing-form')?.click();
    this.logginService.addUser(signinForm.value).subscribe(
      (response: string) => {
        signinForm.reset();
        // successForm.onReset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        signinForm.reset();
      }
    );
}
getLaspPage(){
  return localStorage.getItem('lastPage');
}

onSubmit(signupForm:NgForm){

  // alert(signupForm.value.name);
  this.loginService.signup(signupForm.value).subscribe(
    (response: string) => {
      // this.signupForm.reset();
      // successForm.onReset();
      // console.log(response);
      alert(response);
      this.router.navigate([localStorage.getItem('lastPage')]);
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
      // signupForm.reset();
    }
  );
}
}
