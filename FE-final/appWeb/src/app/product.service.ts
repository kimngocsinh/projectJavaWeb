import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from './models/product';
import { Observable } from 'rxjs';
import { Page } from './models/page';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private URL_API:string = 'http://localhost:8080/api/products';

  constructor(private http:HttpClient) { }

  public getAllProducts(product:Product):Observable<Page>{
    return this.http.post<Page>(`${this.URL_API}/list`, product);
  }

  public addProduct(product:Product):Observable<Product>{
    return this.http.post<Product>(`${this.URL_API}/add-product`, product);
  }

  public updateProduct(product:Product, id:number):Observable<Product>{
    return this.http.put<Product>(`${this.URL_API}/update-product/${id}`, product);
  }

  public deleteProduct(id:number):Observable<void>{
    return this.http.delete<void>(`${this.URL_API}/delete-product/${id}`);
  }
}
