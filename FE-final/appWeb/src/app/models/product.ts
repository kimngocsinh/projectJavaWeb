import { Category } from './category';
import {  supplier } from './supplier';

export class Product{
    id:number;
    name:string = "";
    description:string;
    costPrice?:number;
    currentQuantity?:number;
    category?:Category;
    supplier?:supplier;
    createdDate?:string;
    modifiedDate?:string;
    createdBy?:string;
    modifiedBy?:string;
    page:number = 0;
    size:number = 10;
    startDate?:string;
    endDate?:string;
}


