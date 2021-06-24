export class Contact {
    _id?:string;
    first_name:string;
    last_name:string;
    phone:string;

    constructor(id:string,fname:string,lname:string,phone:string){
        this._id=id;
        this.first_name=fname;
        this.last_name=lname;
        this.phone = phone;
    }

}
