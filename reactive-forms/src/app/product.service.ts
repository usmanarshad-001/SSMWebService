import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Product } from './Interfaces/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  // headers = new HttpHeaders({'Access-Control-Allow-Origin' : '*'})
  path: string="http://localhost:8080/productcategory";
  productPath: string="http://localhost:8080/product";
  constructor(private http: HttpClient) { }
  
  getProductTypeList():Observable<any[]>{
    return this.http.get<any[]>(this.path);
  }
  getProductTypeById(id: number): Observable<any>{
    let concated='/'+id;
    return this.http.get<any>(this.path.concat(concated));
  }

  //Get product items
  getProductList():Observable<any[]>{
    return this.http.get<any[]>(this.productPath);
  }
  getSingleProduct(id: number):Observable<any>{
    return this.http.get<any>(this.productPath+'/'+id);
  }
  insertProduct(data){
    return this.http.post(this.productPath, data)
              .pipe(catchError(this.errorHandler))
  }
  updateProduct(data, id: number){
    this.productPath=this.productPath+ '/'+ id;
    return this.http.put(this.productPath, data)
                .pipe(catchError(this.errorHandler));
  }
  deleteProduct(id: number){
    this.productPath=this.productPath+ '/'+ id;
    return this.http.delete(this.productPath)
                .pipe(catchError(this.errorHandler));
  }
  errorHandler(error: HttpErrorResponse){
    return throwError(error);
  }

}

