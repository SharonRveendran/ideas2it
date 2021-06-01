import { Component, OnInit } from '@angular/core';
import { TodoService } from '../todo.service';
import { Router } from '@angular/router';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {
  newCategoryName ="";
  clickCount =1;
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
    if(this.todoService.subTaskContainerVisibility) {
      this.todoService.subTaskContainerVisibility = false;
    } else {
      if (this.clickCount != 1) {
        this.todoService.subTaskContainerVisibility = true;
        this.clickCount++;
      }
    }
    this.todoService.taskContainerVisibility = true;
    this.todoService.currentCategoryId = categoryId;
    this.todoService.currentCategoryName = categoryName;
    this.router.navigate(["todo/home"], {queryParams:{id:categoryId,name:categoryName}});
    
    //this.categoryList = this.todoService.getCategoryList();\
    //this.categoryList = this.todoService.getCategoryList();
    //alert("RENDER TASK");
    //this.ngOnInit();
  }
}
