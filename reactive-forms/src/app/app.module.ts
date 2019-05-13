import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms'


import { AppRoutingModule, routingModules } from './app-routing.module';
import { AppComponent } from './app.component';
import { InsertProductComponent } from './insert-product/insert-product.component';
import { HttpClientModule } from '@angular/common/http';
import { ProductService } from './product.service';
import { ProductListComponent } from './product-list/product-list.component';
import { EditProductComponent } from './edit-product/edit-product.component';


@NgModule({
  declarations: [
    AppComponent,
    routingModules
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
