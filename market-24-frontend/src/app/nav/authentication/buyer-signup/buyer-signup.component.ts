import { LoginService } from './../../../services/login.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, NgForm, Validators } from '@angular/forms';
import { User } from 'src/app/interfaces/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-buyer-signup',
  templateUrl: './buyer-signup.component.html',
  styleUrls: ['./buyer-signup.component.css']
})
export class BuyerSignupComponent implements OnInit {
  // logginService: any;
  user!: User;

  // signupForm = this.fb.group({
  //   name:['',Validators.required],
  //   surname:['',Validators.required],
  //   username:['',Validators.required],
  //   phone:['',Validators.required],
  //   email:['',Validators.required],
  //   password:['',Validators.required]
  // })

  constructor(
    private fb: FormBuilder,
    private loginService: LoginService,
    private router: Router,) { }

  ngOnInit(): void {
  }

  // signup(signinForm: NgForm){
  //   document.getElementById('signing-form')?.click();
  //   this.logginService.addUser(signinForm.value).subscribe(
  //     (response: string) => {
  //       signinForm.reset();
  //       // successForm.onReset();
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //       signinForm.reset();
  //     }
  //   );
  //   // localStorage.setItem('role',this.user.role)
  // }

  getLaspPage(){
    return localStorage.getItem('lastPage');
  }

  onSubmit(signupForm:NgForm){

    // alert(signupForm.value.name);
    this.loginService.addUser(signupForm.value).subscribe(
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
