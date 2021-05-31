import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  constructor() { }
  taskContainerVisibility = false;
  currentCategoryName:any;
  currentCategoryId : any;
  currentTaskList :any;
  currentTask :any;
  currentTaskId :any;
  subTaskContainerVisibility = true;
  categoryList =[
    {
      id : "category1",
      icon : "fa fa-home",//sun
      name : "My Day",
      taskList : []
  },
  {
      id : "category2",
      icon : "fa fa-star",
      name : "Important",
      taskList : []
  },
  {
      id : "category3",
      icon : "fa fa-calendar",
      name : 'Planned',
      taskList : []
  },
  {
      id : "category4",
      icon : "fa fa-user",
      name : "Assign to you",
      taskList : []
  },
  {
      id : "category5",
      icon : "fa fa-home",
      name : "Task",
      taskList : []
  }
  ]
  getCategoryList() {
    return this.categoryList;
  }
  addCategory(obj: { id: string; icon: string; name: string; taskList: never[]; }) {
    this.categoryList.push(obj);
  }
  getTaskList() {
    for(const obj of this.categoryList) {
      if(obj.id == this.currentCategoryId) {
        this.currentTaskList = obj.taskList;
      }
    }
    return this.currentTaskList;
  }
}
