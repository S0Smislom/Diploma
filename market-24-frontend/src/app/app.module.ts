import { HomeContentComponent } from './body/home-content/home-content.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
// import { MainComponent } from './body/main/main.component';
import { GalleryComponent } from './body/gallery/gallery.component';
import { AboutUsComponent } from './body/about-us/about-us.component';
import { NavComponent } from './nav/nav.component';
import { FooterComponent } from './footer/footer.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { AuthorisationComponent } from './body/authorisation/authorisation.component';
import { LoginService } from './services/login.service';

import { NgxsStoragePluginModule } from '@ngxs/storage-plugin';
import { NgxsModule } from '@ngxs/store';
// import { UserRoleState } from './UserRoleState';
import { JwtModule } from '@auth0/angular-jwt';
import { CardPageComponent } from './card-page/card-page.component';
import { BuyerLoginComponent } from './nav/authentication/buyer-login/buyer-login.component';
import { BuyerSignupComponent } from './nav/authentication/buyer-signup/buyer-signup.component';
import { SellerSignupComponent } from './nav/authentication/seller-signup/seller-signup.component';
import { SellerLoginComponent } from './nav/authentication/seller-login/seller-login.component';
import { SignupComponent } from './nav/authentication/signup/signup.component';
import { LoginComponent } from './nav/authentication/login/login.component';
import { HomeComponent } from './home/home.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { UserPanelComponent } from './user-panel/user-panel.component';
import { SellerPanelComponent } from './seller-panel/seller-panel.component';
// import { Home } from './body/home.content/home.content.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    // HomeContentComponent,
    GalleryComponent,
    AboutUsComponent,
    NavComponent,
    FooterComponent,
    CardPageComponent,
    BuyerLoginComponent,
    BuyerSignupComponent,
    SellerSignupComponent,
    SellerLoginComponent,
    SignupComponent,
    LoginComponent,
    AdminPanelComponent,
    UserPanelComponent,
    SellerPanelComponent,

    // AuthorisationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

    // NgxsModule.forRoot([UserRoleState]),
    // NgxsStoragePluginModule.forRoot(),

    // JwtModule.forRoot({})
  ],
  providers: [LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
