import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductComment } from '../interfaces/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  // private comments:ProductComment[] = []
  private apiServerUrl = 'http://localhost:8080/product'

  constructor(private http: HttpClient) { }

  public getCommments(productId:number): Observable<ProductComment[]>{
    return this.http.get<any>(`${this.apiServerUrl}/comment/find/${productId}`);
  }

  public deleteComment(commentId:number):Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/comment/delete/${commentId}`);
  }

  public addComment(comment:ProductComment):Observable<ProductComment>{
    return this.http.post<ProductComment>(`${this.apiServerUrl}/comment/add`,comment)
  }
}
