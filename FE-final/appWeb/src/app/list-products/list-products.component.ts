import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Page } from '../models/page';
import { Product } from '../models/product';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { CagegoryService } from '../cagegory.service';
import { SupplierService } from '../supplier.service';
import { Category } from '../models/category';
import { supplier } from '../models/supplier';

@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.css']
})
export class ListProductsComponent implements OnInit{

  public productList:Page;
  public productData:Product[];
  searchValue:string="";
  CurrentPage:number=0;
  startDate:string;
  endDate:string;



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

  getData():void{
    const product = new Product();
    product.startDate = this.startDate;
    product.endDate = this.endDate;
    this.productService.getAllProducts(product).subscribe(respone =>{
      this.productList = respone;
      this.CurrentPage = respone.number;
    })
  }

  public searchStartDate(value:string){
    this.startDate = value;
    this.checkValue();
  }
  
  searchEndDate(value:string){
    this.endDate = value;
    this.checkValue()
  }
  
  public checkValue(){
    if(this.startDate != null && this.endDate != null){
      this.getData();
    }
  }
  
  
  
  public gotoPage(searchName:string = "", page:number = 0, size:number = 10):void{
    const product = new Product();  
    product.name = searchName;
    product.page = page;
    product.size = size;
    product.startDate = this.startDate;
    product.endDate = this.endDate;
    this.productService.getAllProducts(product).subscribe(respone => {
      this.productList = respone;
      this.CurrentPage = respone.number;
    })
  }
  
  
  
  public handleNext(name:string = "", page:number = 0, size:number = 10):void{
    const product = new Product();  
    product.name = name;
    product.page = page;
    product.size = size;
    this.productService.getAllProducts(product).subscribe(respone => {
      this.productList = respone;
      this.CurrentPage = respone.number;
    })
  }

  public addProduct(addForm:NgForm):void{
    this.productService.addProduct(addForm.value).subscribe((respone:Product) =>{
      this.getData();
      console.log(respone);
    })
  }

  public updateProduct(editForm:NgForm,id:number):void{
    this.productService.updateProduct(editForm.value,id).subscribe((response:Product)=>{
  
      this.getData();
      console.log(response);
    }),
    (error:HttpErrorResponse)=>{
      alert(error.message);
    }
  
  }
  public DeleteProduct(id:any):void{
    this.productService.deleteProduct(id).subscribe(
      (response:void)=>{
        this.getData();
      },
      (error:HttpErrorResponse)=>{
        alert(error.message);
      }
  
    )
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
