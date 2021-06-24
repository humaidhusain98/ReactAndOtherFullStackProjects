import { Component } from '@angular/core';
import {Contact} from './contact';
import {ContactServiceService} from './contact-service.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Welcome to Node+ Angular Test';
  ContactList:Contact[];
  closeResult: string;
  fname:string;
  lname:string;
  mobile:string;
  

  constructor(private serv:ContactServiceService,private modalService: NgbModal)
  {
    this.ContactList=[];
    this.getContacts();
  }

  getContacts(){
      this.serv.getContacts().subscribe((contacts:Contact[])=>{

        this.ContactList=contacts;
        console.log(this.ContactList);
      })
  }

  //modal down below

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  addContact(){
    var contact= new Contact("-1",this.fname,this.lname,this.mobile);
    this.fname="";
    this.lname="";
    this.mobile="";
    this.serv.postContact(contact).subscribe((resp)=>{
      this.getContacts();
    })

  }


}