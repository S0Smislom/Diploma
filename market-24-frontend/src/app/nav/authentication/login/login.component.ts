import { Component, OnInit } from '@angular/core';

export type AuthenticationType = 'buyer'|'seller';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  authenticationType: AuthenticationType = 'buyer';
  constructor() { }

  ngOnInit(): void {
  }

  showBuyerAuthentication(){
    // console.log("AuthType " + this.authenticationType ==='buyer');
    return this.authenticationType === 'buyer';
  }

  // tslint:disable-next-line: typedef
  showSellerAuthentication(){
    // console.log("AuthType " + this.authenticationType ==='seller');
    return this.authenticationType === 'seller';
  }

    getauthType(){
      console.log(this.authenticationType);
      return this.authenticationType;
    }

  toggleAuthentication(type:AuthenticationType){

    this.authenticationType = type;
    console.log(this.authenticationType);
  }

}
