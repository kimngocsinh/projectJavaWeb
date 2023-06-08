import { Component } from '@angular/core';
import { Page } from '../models/page';
import { Product } from '../models/product';
import { ProductService } from './../product.service';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { CagegoryService } from './../cagegory.service';
import { Category } from './../models/category';
import { SupplierService } from './../supplier.service';
import { supplier } from '../models/supplier';


@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent {
  public productList:Page;
  public productData:Product[];
  public product: Product = new Product();
  searchValue:string="";
  CurrentPage:number=0;

  

  public categories:Category[];
  public category:Category;

  public suppliers:supplier[];
  public supplier:supplier[];

  constructor(private productService : ProductService, private categoryService :CagegoryService, private supplierService:SupplierService){}

  ngOnInit(){
    this.getData();
    this.getCategories();
    this.getSuppliers();
  }

  getData():void {
    const product = new Product();
    this.productService.getAllProducts(product).subscribe(respone =>{
      this.productList = respone;
      this.CurrentPage = respone.number;
    })
  }

  public addProduct(addForm:NgForm):void{
    this.productService.addProduct(addForm.value).subscribe((respone:Product) =>{
      this.getData();
      console.log(respone);
    }),
    (error:HttpErrorResponse) =>{
      alert(error.message);
    }
  }

  public getCategories():void{
    this.categoryService.getCategories().subscribe(
      (response: Category[]) => {
        this.categories = response;
        console.log(this.categories);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  public getSuppliers():void{
    this.supplierService.getsuppliers().subscribe(
      (response: supplier[]) => {
        this.suppliers = response;
        console.log(this.suppliers);
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
