import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from './models/category';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CagegoryService {
  private url:string = 'http://localhost:8080/api/categories';

  constructor(private http:HttpClient) {
  }

  public getCategories():Observable<Category[]>{
    return this.http.get<Category[]>(`${this.url}/list`);
   }
 
}
