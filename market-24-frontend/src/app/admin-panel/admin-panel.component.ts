import { HttpErrorResponse } from '@angular/common/http';
import { UserService } from './../services/user.service';
import { Component, OnInit } from '@angular/core';
import { SellerService } from '../services/seller.service';
import { User } from '../interfaces/user';
import { Seller } from '../interfaces/seller';
import { checkRole } from '../functions/checkRole';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {
  public users: User[] = [];
  public editUser!: User;
  public deleteUser!: User;

  public sellers: Seller[] = [];
  public editSeller!: Seller;
  public deleteSeller!: Seller;


  constructor(
    private userService: UserService,
    private sellerService: SellerService,
  ) { }

  ngOnInit(): void {
    this.getUsers();
    this.getSellers();
  }

  public getUsers(): void{
    this.userService.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
        console.log(this.users);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getSellers(): void{
    this.sellerService.getSellers().subscribe(
      (response: Seller[]) => {
        this.sellers = response;
        console.log(this.sellers);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateUser(user: User): void{
    this.userService.updateUser(user).subscribe(
      (response: User) => {
        console.log(response);
        this.getUsers();
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
        this.getSellers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteUser(userId: number): void{
    this.userService.deleteUser(userId).subscribe(
      (response: void) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteSeller(sellerId: number): void{
    this.sellerService.deleteSeller(sellerId).subscribe(
      (response: void) => {
        console.log(response);
        this.getSellers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public editUserModal(user: User): void{
    this.editUser = user;
  }

  public deleteUserModal(user: User): void{
    this.deleteUser = user;
  }

  public editSellerModal(seller: Seller): void{
    this.editSeller = seller;
  }

  public deleteSellerModal(seller: Seller): void{
    this.deleteSeller = seller;
  }

  public usersIsEmpty(): boolean{
    if (this.users.length > 0){
      return true;
    }
    return false;
  }

  public sellersIsEmpty(): boolean{
    if (this.sellers.length > 0){
      return true;
    }
    return false;
  }

  public checkRole(): boolean{
    return checkRole() === 'ROLE_ADMIN';
  }
}
