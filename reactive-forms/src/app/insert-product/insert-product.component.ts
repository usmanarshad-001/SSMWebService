import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../product.service';
import { forbiddenNameValidator } from '../shared/validator';

@Component({
  selector: 'app-insert-product',
  templateUrl: './insert-product.component.html',
  styleUrls: ['./insert-product.component.css']
})
export class InsertProductComponent implements OnInit {
  insertProduct: FormGroup;
  productTypes=[];
  hasError=true;
  submitted=false;
  constructor(private formBuilder: FormBuilder,private service: ProductService) { }

  
  ngOnInit() {
    this.insertProduct=this.formBuilder.group({
      product_NAME: [''],
      product_DESCRIPTION:[''],
      product_PRICE:[''],      
      product_STOCK:[''],
      productcategory_ID:['default']
    });
    this.getProductTypes();
  }

  onSubmit(){
    console.log(this.insertProduct.value);
    this.service.insertProduct(this.insertProduct.value)
      .subscribe(data=>console.log(data),
                error=>console.log(error)
                );
    this.submitted=true;
  }

  getProductTypes(){
    this.service.getProductTypeList().subscribe(data=>this.productTypes=data);    
  }
  validateType(value){    
    if(value=="default")
      this.hasError=true;
    else
      this.hasError=false;
    }
    get productcategory_ID(){ 
      return this.insertProduct.get('productcategory_ID');
    }
    get product_NAME(){
      return this.insertProduct.get('product_NAME');
    }
  }