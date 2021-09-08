import { Router } from '@angular/router';
import { ChangePassword } from './../interfaces/changePassword';
import { HttpErrorResponse } from '@angular/common/http';
import { UserService } from './../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../interfaces/user';
import { checkRole, getUsername } from '../functions/checkRole';
import { Product } from '../interfaces/product';

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.css']
})
export class UserPanelComponent implements OnInit {

  public user!: User;
  public changePassword!: ChangePassword;
  // public currentUsername!: string;

  constructor(
    private userService: UserService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.getUser();
  }


  public getUser(): void{
    this.userService.getUserByUsername(getUsername()).subscribe(
      (response: User) => {
        this.user = response;
        console.log(this.user);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateUser(user: User): void{
    this.userService.updateUser(user).subscribe(
      (response: User) => {
        console.log(response);
        this.getUser();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getNewPassword(changePassword: ChangePassword): void{
    if(changePassword.newPassword === changePassword.reNewPassword){
      this.userService.changePassword(changePassword).subscribe(
        (response: void) => {
          alert(response);
          // localStorage.setItem('token', '');
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
    else{
      alert('Неправильно введен новый пароль');
    }

  }

  public roleUser(){
    return checkRole() === "ROLE_USER";
  }

  public productToString(product: Product){
    this.router.navigate(['product'],  { queryParams: {
      id: product?.id,
    } });
  }

  public dislikeProduct(product: Product){
    this.userService.dislikeProduct(product.id, this.user.id).subscribe(
      (response: User)=>{
        this.user = response;
        console.log(this.user);
      },
      (error: HttpErrorResponse)=>{
        alert(error)
      }
    )
  }
}
