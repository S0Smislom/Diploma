import { UserService } from './../services/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ProductService } from 'src/app/services/product.service';
import { NgForm } from '@angular/forms';
import { Product } from './../interfaces/product';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { CommentService } from '../services/comment.service';
import { ProductComment } from './../interfaces/comment'
import { checkRole, getId } from '../functions/checkRole';
import { User } from '../interfaces/user';


@Component({
  selector: 'app-card-page',
  templateUrl: './card-page.component.html',
  styleUrls: ['./card-page.component.css']
})
export class CardPageComponent implements OnInit {
  product!: Product;
  productId!: number;

  comments: ProductComment[] = [];
  deleteComment!: ProductComment;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private commentService: CommentService,
    private productService: ProductService,
    private userService: UserService,
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      // this.param1 = params['id'];
      // this.product.name = params['name'];
      this.productId = params['id']
      this.getProduct(this.productId);
      this.getComments(this.productId);
      // this.getComments();
  });

  }

  getProduct(id: number){
    // this.product = this.productService.getProductById(this.id);

    this.productService.getProductById(id).subscribe(
      (response: Product) => {
        this.product = response;
        console.log(this.product);
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );

  }

  logedIn(){
    let check = false;
    if( localStorage.getItem('token') === '' || localStorage.getItem('token') === null || localStorage.getItem('token') === undefined){
      check = true;
    }
    console.log(check);
    return check;
  }

  getComments(productId: number){
    // let productId = this.id
    this.commentService.getCommments(Number(productId)).subscribe(
      (response: ProductComment[]) => {
        this.comments = response;
        console.log(this.comments);
      }
    )
  }

  public onAddComment(addForm:NgForm){
    console.log(addForm.value);
    this.commentService.addComment(addForm.value).subscribe(
      (response: any) => {
        console.log(response);
        this.getComments(this.productId);
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    )

  }



  public getUserId(): number {
    return getId();
  }

  public onDeleteComment(comment: ProductComment){
    this.commentService.deleteComment(comment.id).subscribe(
      (response: any) => {
        console.log(response);
        this.getComments(this.productId);
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    )
  }
  roleAdmin(){
    return checkRole() === 'ROLE_ADMIN';
  }

  roleUser(){
    return checkRole() === 'ROLE_USER';
  }



  likeProduct(productId: number){
    this.userService.likeProduct(productId, this.getUserId()).subscribe(
      (response: User)=> {
        console.log(response);
        alert("Товар был добавлен в избранное")
      },
      (error:HttpErrorResponse)=>{
        alert(error);
      }
    )
  }

}
