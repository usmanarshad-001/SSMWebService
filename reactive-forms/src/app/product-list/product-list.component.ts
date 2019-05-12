import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import {  Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  private productList=[];
  constructor(
    private service: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.service.getProductList()
      .subscribe(data=>this.productList=data,
                error=>alert(error));
  }
  onClick(value){
    // alert('I am clicked '+value);
    // this.router.navigate(['/product-list/edit-product', value])
    this.router.navigate([{relativeTo: this.route},'edit-product',value])
  }
  deleteProduct(value, name){
    console.log(value);    
    if(confirm("Are you sure to delete "+name+" ?")){
      this.service.deleteProduct(value)
      .subscribe(
        data=>console.log(data),
        error=>console.error(error)      
      );
    }
    this.router.navigate(['product-list']);
  }
}
