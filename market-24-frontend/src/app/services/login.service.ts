import { Authentification } from './../interfaces/authentification';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from '../interfaces/login';
import { User } from '../interfaces/user';
// import { Authentification} from '../interfaces/authentification'

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiServerUrl = 'http://localhost:8080'


  constructor(private http: HttpClient) { }

  // public getUsers(): Observable<User[]>{
  //   return this.http.get<any>(`${this.apiServerUrl}/product/all`)
  // }
  public addUser(user: Login): Observable<any>{
    const options = {responseType: 'text' as 'json'};
    return this.http.post<any>(`${this.apiServerUrl}/signup`, user, options);
  }

  public loginUser(user: Login): Observable<Authentification>{
    return this.http.post<Authentification>(`${this.apiServerUrl}/login`, user);
  }

}
