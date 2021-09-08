
import { Component, OnInit } from '@angular/core';
import { Authentification } from '../interfaces/authentification';

import { checkRole } from '../functions/checkRole';



@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

    auth!: Authentification;


  constructor(

  ) {

  }

  ngOnInit(): void {
  }

  rememberLink(){
    localStorage.setItem('lastPage', window.location.href);
  }

  get checkRole(){
    return checkRole();
  }

  logout(){
    localStorage.setItem('token', '');
  }
}
