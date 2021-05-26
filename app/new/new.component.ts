import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-new',
  templateUrl: './new.component.html',
  styleUrls: ['./new.component.scss']
})
export class NewComponent implements OnInit {
  para:string = ""
  constructor() { }

  ngOnInit(): void {
  }
  onclick() {
    this.para = "vignesh"
  }
}
