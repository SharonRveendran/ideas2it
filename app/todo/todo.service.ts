import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  constructor() { }
  categoryList =[
    {
      id : "category1",
      icon : "fa fa-star",//sun
      name : "My Day"
  },
  {
      id : "category2",
      icon : "fa fa-star",
      name : "Important"
  },
  {
      id : "category3",
      icon : "fa fa-calendar",
      name : 'Planned'
  },
  {
      id : "category4",
      icon : "fa fa-user",
      name : "Assign to you"
  },
  {
      id : "category5",
      icon : "fa fa-home",
      name : "Task"
  }
  ]
  getCategoryList() {
    return this.categoryList;
  }
  addCategory(obj: { id: string; icon: string; name: string; }) {
    this.categoryList.push(obj);
  }
}
