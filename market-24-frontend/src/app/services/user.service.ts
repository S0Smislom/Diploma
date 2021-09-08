import { ChangePassword } from './../interfaces/changePassword';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl = 'http://localhost:8080';
  private users: User[] = [];

  constructor(
    private http: HttpClient,
  ) { }

  private createAuthenticationHeader(){

    return "Bearer "+ localStorage.getItem('token');
  }

  public getUsers(): Observable<User[]>{

    // let headers = new HttpHeaders();
    // headers.append("Authorization", this.createAuthenticationHeader());

    const options = {
      headers: new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`),
    };

    return this.http.get<any>(`${this.apiServerUrl}/user/all`, options);
  }

  public updateUser(user: User): Observable<User>{
    return this.http.put<User>(`${this.apiServerUrl}/user/update`, user);
  }

  public deleteUser(userId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/user/delete/${userId}`);
  }

  public getUserByUsername(username: string): Observable<User>{
    return this.http.get<User>(`${this.apiServerUrl}/user/find/${username}`);
  }

  public changePassword(changePassword: ChangePassword): Observable<void>{
    const options = {responseType: 'text' as 'json'};
    return this.http.put<void>(`${this.apiServerUrl}/repassword`, changePassword, options);
  }

  public likeProduct(productId: number, userId: number): Observable<User>{
    return this.http.get<User>(`${this.apiServerUrl}/user/like/${productId}:${userId}`);
  }

  public dislikeProduct(productId: number, userId: number): Observable<User>{
    return this.http.delete<User>(`${this.apiServerUrl}/user/dislike/${productId}:${userId}`);
  }
}
