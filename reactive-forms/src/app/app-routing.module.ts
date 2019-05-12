import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { InsertProductComponent } from './insert-product/insert-product.component';
import { EditProductComponent } from './edit-product/edit-product.component';

const routes: Routes =[
  {path: '', redirectTo: '/product-list', pathMatch:'full'},
  {path: 'product-list',component: ProductListComponent},
  {path: 'edit-product/:id', component: EditProductComponent},
  {path: 'insert-item', component: InsertProductComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingModules=[
  ProductListComponent,
  EditProductComponent,
  InsertProductComponent
]
