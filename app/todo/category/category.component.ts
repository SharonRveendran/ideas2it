import { Component, OnInit } from '@angular/core';
import { TodoService } from '../todo.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {
  newCategoryName ="";
  constructor(private todoService: TodoService) { }
  categoryList: any ;
  categoryId = 6;
  ngOnInit(): void {
    this.categoryList = this.todoService.getCategoryList();
  }
  addCategory() {
    console.log(this.newCategoryName);
    if(this.newCategoryName == "") {
      this.newCategoryName = "Untitled list"
    }
    const obj = {
      id: "category" + this.categoryId++,
      icon: 'fa fa-list',
      name: this.newCategoryName
    }
    this.newCategoryName="";
    this.todoService.addCategory(obj);
  }
}
