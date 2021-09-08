import { SellerService } from './../services/seller.service';
import { ChangePassword } from './../interfaces/changePassword';
import { Component, OnInit } from '@angular/core';
import { Seller } from '../interfaces/seller';
import { checkRole, getUsername } from '../functions/checkRole';
import { HttpErrorResponse } from '@angular/common/http';
import { Product } from '../interfaces/product';
import { ProductService } from '../services/product.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-seller-panel',
  templateUrl: './seller-panel.component.html',
  styleUrls: ['./seller-panel.component.css']
})
export class SellerPanelComponent implements OnInit {

  public seller!: Seller;
  public changePassword!: ChangePassword;

  public products: Product[] = [];
  public editProduct!: Product;
  public deleteProduct!: Product;

  constructor(
    private sellerService: SellerService,
    private productService: ProductService,
  ) { }

  ngOnInit(): void {
    this.getSeller();
    this.getProducts();
  }

  public getSeller(): void{
    this.sellerService.getSellerByUsername(getUsername()).subscribe(
      (response: Seller) => {
        this.seller = response;
        console.log(this.seller);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateSeller(seller: Seller): void{
    this.sellerService.editSeller(seller).subscribe(
      (response: Seller) => {
        console.log(response);
        this.getSeller();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public getNewPassword(changePassword: ChangePassword): void{
    if(changePassword.newPassword === changePassword.reNewPassword){
      this.sellerService.changePassword(changePassword).subscribe(
        (response: void) => {
          alert(response);
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      )
    }
    else{
      alert("Неправильно введен новый пароль")
    }
  }

  public roleSeller(){
    return checkRole() === "ROLE_SELLER";
  }

  public getProducts(){
      this.productService.findProductByUsername(getUsername()).subscribe(
        (response: Product[]) => {
          this.products = response;
          console.log(this.products);
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      )
  }

  public onAddProduct(addForm: NgForm): void{
    document.getElementById('add-product-form')?.click();
    this.productService.addProduct(addForm.value).subscribe(
      (response: Product) => {
        console.log(response);
        this.getProducts();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateProduct(product: Product): void {
    this.productService.updateProduct(product).subscribe(
      (response: Product) => {
        console.log(response);
        this.getProducts();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onDeleteProduct(productId: number): void {
    this.productService.deleteProduct(productId).subscribe(
      (response: void) => {
        console.log(response);
        this.getProducts();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public editProductModal(product: Product):void{
    this.editProduct = product;
  }

  public deleteProductModal(product: Product): void{
    this.deleteProduct = product;
  }

}
