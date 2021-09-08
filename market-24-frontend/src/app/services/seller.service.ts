import { ChangePassword } from './../interfaces/changePassword';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Seller } from '../interfaces/seller';

@Injectable({
  providedIn: 'root'
})
export class SellerService {

  private apiServerUrl = 'http://localhost:8080';

  private sellers: Seller[] = [];

  constructor(
    private http: HttpClient
  ) { }

  public getSellers(): Observable<Seller[]>{
    return this.http.get<any>(`${this.apiServerUrl}/seller/all`);
  }

  public editSeller(seller: Seller): Observable<Seller>{
    return this.http.put<Seller>(`${this.apiServerUrl}/seller/update`, seller);
  }

  public deleteSeller(sellerId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/seller/delete/${sellerId}`);
  }

  public getSellerByUsername(username: string): Observable<Seller>{
    return this.http.get<Seller>(`${this.apiServerUrl}/seller/find/${username}`);
  }

  public changePassword(changePassword: ChangePassword): Observable<void>{
    const options = {responseType: 'text' as 'json'};
    return this.http.put<void>(`${this.apiServerUrl}/seller_repassword`, changePassword, options);
  }
}
