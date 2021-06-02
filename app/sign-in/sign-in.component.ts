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

  /**
   * click method for form submit
   * @param form  ng formm object
   */
  onsubmit(form: NgForm) {
    if (this.signInService.isExist(form.value.email, form.value.password)) {
      if (form.value.email != "" && form.value.password != "") {
      this.router.navigate(["home"]);
      console.log(form);
      }
    } else {
      if (form.value.email != "" && form.value.password != "") {
        this.error = "Email id is incorrect...!!!";
        form.reset();
      }
    }
  }
  
  ngOnInit(): void {
  }

}
