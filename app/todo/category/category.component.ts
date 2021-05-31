import { Component, OnInit } from '@angular/core';
import { TodoService } from '../todo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {
  newCategoryName ="";
  constructor(private todoService: TodoService, private router: Router) { }
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
      name: this.newCategoryName,
      taskList : []
    }
    this.newCategoryName="";
    this.todoService.addCategory(obj);
  }
  renderTask(categoryId: any , categoryName: any) {
    this.todoService.taskContainerVisibility = true;
    this.todoService.currentCategoryId = categoryId;
    this.todoService.currentCategoryName = categoryName;
    this.router.navigate(["todo/home"]);//, {queryParams:{id:categoryId}});
    //this.categoryList = this.todoService.getCategoryList();
  }
}
