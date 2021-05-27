import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { SignInService } from "./sign-in.service"

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

  constructor(private signInService: SignInService, private router: Router) { }
  error: string = "";
  data = {
    email: "",
    password: ""
  }
  onsubmit(form: NgForm) {
    //if(this.signInService.isExist(this.data.email)) {
    if (this.signInService.isExist(form.value.email, form.value.password)) {
      if (form.value.email != "" && form.value.password != "") {
      this.router.navigate(["todo/header"]);
      console.log(form);
      }
    } else {
      if (form.value.email != "" && form.value.password != "") {
        this.error = "Create new account...!!!";
        form.reset();
      }
    }
  }
  ngOnInit(): void {
  }

}
