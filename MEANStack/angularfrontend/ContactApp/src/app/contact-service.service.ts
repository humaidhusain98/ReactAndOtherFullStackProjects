import { Injectable } from '@angular/core';
import { fromEventPattern } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Contact} from './contact';

@Injectable({
  providedIn: 'root'
})
 export class ContactServiceService {

  constructor( private http: HttpClient) { 

  }

  getContacts(){
    return this.http.get<Contact[]>('http://localhost:3000/api/contact');
  }

  postContact(Contac:Contact){
    return this.http.post('http://localhost:3000/api/contact',Contac);
  }
}
