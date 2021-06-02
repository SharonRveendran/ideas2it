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
  clickCount =1;
  constructor(private todoService: TodoService, private router: Router) { }
  categoryList: any ;
  categoryId = 6;

  ngOnInit(): void {
    this.categoryList = this.todoService.getCategoryList();
   
  }

  /**
   * Method to add category
   */
  addCategory() {
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

  /**
   * Method to render task container
   * @param categoryId category id
   * @param categoryName category name
   */
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
    this.todoService.setCategoryId(categoryId);
    this.todoService.currentCategoryName = categoryName;
    this.router.navigate(["todo/home"], {queryParams:{id:categoryId,name:categoryName}});
  }
}
