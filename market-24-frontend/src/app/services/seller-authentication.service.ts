import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Seller } from '../interfaces/seller';
import { Authentification } from '../interfaces/authentification';

@Injectable({
  providedIn: 'root'
})
export class SellerAuthenticationService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http:HttpClient) { }

  public signup(seller:Seller): Observable<any>{
    const options = {responseType: 'text' as 'json'};
    return this.http.post<any>(`${this.apiServerUrl}/seller_signup`, seller, options);
  }

  public login(seller: Seller): Observable<Authentification>{
    return this.http.post<Authentification>(`${this.apiServerUrl}/seller_login`,seller);
  }
}
