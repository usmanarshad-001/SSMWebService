import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { forbiddenNameValidator } from '../shared/validator';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { ProductService } from '../product.service';
import { Product } from '../Interfaces/product';
// import { DISABLED } from '@angular/forms';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  private submitted= false;
  private editProduct: FormGroup;
  private productid: number;  
  private selectedproduct;
  private selectedProductType;
  private enteredproduct=[];

  constructor(private formbuilder: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private service: ProductService) { }
  ngOnInit() {    
    this.editProduct=this.formbuilder.group({      
      product_NAME: [''],
      product_PRICE:[''],      
      product_STOCK:[''],
      productcategory_ID:[''],
      product_DESCRIPTION:['']  
    })
    this.getControl("product_NAME");
    this.route.paramMap.subscribe((params: ParamMap)=>{
      this.productid=parseInt(params.get('id'))
    });    
    //Getting selected single product type    

      //Getting a selected Product
      this.service.getSingleProduct(this.productid)
       .subscribe(data=>{
         this.selectedproduct=data;
         console.log(this.selectedproduct);
         this.populateForm();
          })
  }
  populateForm(){
    // console.log(this.selectedproduct);
    this.editProduct.setValue({
      product_NAME: [this.selectedproduct.product_NAME],
      product_PRICE:[this.selectedproduct.product_PRICE],      
      product_STOCK:[this.selectedproduct.product_STOCK],
      productcategory_ID:[this.selectedproduct.productcategory_ID.productcatergory_NAME],
      product_DESCRIPTION:[this.selectedproduct.product_DESCRIPTION]
    })
  }
  getControl(value){
    return this.editProduct.get(value);
  }
  //Editing a record values
  onSubmit(){
    console.log(this.editProduct);
    var object = {
      product_DESCRIPTION: this.getControl('product_DESCRIPTION').value,
      product_NAME: this.editProduct.value.product_NAME[0],
      product_STOCK: this.editProduct.value.product_STOCK[0] 
    }
    this.service.updateProduct(object, this.productid)
      .subscribe(data=>{
        console.log(data);  
        this.submitted=true;      
      }),
      error=>alert(error);
    console.log(object);        
  }
}
