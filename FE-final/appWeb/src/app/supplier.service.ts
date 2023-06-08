
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { supplier } from './models/supplier';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class SupplierService {
  private url:string = 'http://localhost:8080/api/suppliers';

  constructor(private http:HttpClient) { }

  public getsuppliers():Observable<supplier[]>{
    return this.http.get<supplier[]>(`${this.url}/list`);
   }

}
