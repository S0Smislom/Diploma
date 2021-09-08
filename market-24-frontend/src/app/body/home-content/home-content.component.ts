import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/interfaces/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-home-content',
  templateUrl: './home-content.component.html',
  styleUrls: ['./home-content.component.css']
})
export class HomeContentComponent implements OnInit {
  products: Product[] = [];

  constructor(private productService:ProductService) { }

  ngOnInit(): void {
    this.getProducts()

  }

  public getProducts():void{
    this.productService.getProducts().subscribe(
      (response: Product[])=>{
        this.products = response;
        console.log(this.products);
      },
      (error: HttpErrorResponse)=>{
        alert(error.message)
      }
    )
  }
}
