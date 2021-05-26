import { Injectable } from "@angular/core";

@Injectable({providedIn: 'root'})
 export class SignInService {
     emails = [
         "sharon@ideas2it.com", "vignesh@ideas2it.com"
     ]
     isExist(email:string, password:string) {
        return this.emails.includes(email)&&password!="";
     }
 }