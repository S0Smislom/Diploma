import { Product } from './../interfaces/product';
import { HttpClient } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
// import { Product } from '../interfaces/product';


@Injectable({
  providedIn: 'root'
})
export class ProductService {
  // private apiServerUrl = environment.apiBaseUrl;
  private apiServerUrl = 'http://localhost:8080'

  private products: Product[] = []

  constructor(private http: HttpClient) { }

  public getProducts(): Observable<Product[]>{
    return this.http.get<any>(`${this.apiServerUrl}/product/all`)
  }

  public addProduct(product: Product): Observable<Product>{
    return this.http.post<Product>(`${this.apiServerUrl}/product/add`,product)
  }

  public updateProduct(product: Product): Observable<Product>{
    return this.http.put<Product>(`${this.apiServerUrl}/product/update`,product)
  }

  public deleteProduct(productId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/product/delete/${productId}`);
  }

  public findProductByUsername(username: string): Observable<Product[]>{
    return this.http.get<Product[]>(`${this.apiServerUrl}/product/find/seller_username/${username}`);
  }

  public getProductById(id: number): Observable<Product>{
    return this.http.get<Product>(`${this.apiServerUrl}/product/find/${id}`);
  }


}
