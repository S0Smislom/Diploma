import { SellerPanelComponent } from './seller-panel/seller-panel.component';
import { UserPanelComponent } from './user-panel/user-panel.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { HomeContentComponent } from './body/home-content/home-content.component';
import { SignupComponent } from './nav/authentication/signup/signup.component';
import { LoginComponent } from './nav/authentication/login/login.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './body/about-us/about-us.component';
import { GalleryComponent } from './body/gallery/gallery.component';

import { CardPageComponent } from './card-page/card-page.component';

const routes: Routes = [
  {path: '', component: HomeComponent, children:[
    {path: '', component: HomeContentComponent, pathMatch: 'full'},
    {path: 'main', component: HomeContentComponent},
    {path: 'gallery', component: GalleryComponent},
    {path: 'about_us', component: AboutUsComponent},
    {path: 'product', component: CardPageComponent},
    {path: 'admin', component: AdminPanelComponent},
    {path: 'user', component: UserPanelComponent},
    {path: 'seller', component: SellerPanelComponent},
  ]},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: '**', redirectTo: 'main'},
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [HomeContentComponent,GalleryComponent,AboutUsComponent,CardPageComponent]
