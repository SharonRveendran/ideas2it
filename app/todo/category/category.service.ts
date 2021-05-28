import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  categoryList: any ;
  constructor() { 
    this.categoryList =[
      {
        id : "category1",
        icon : "fa fa-sun",
        name : "My Day"
    },
    {
        id : "category2",
        icon : "far fa-star",
        name : "Important"
    },
    {
        id : "category3",
        icon : "far fa-calendar-alt",
        name : 'Planned'
    },
    {
        id : "category4",
        icon : "far fa-user",
        name : "Assign to you"
    },
    {
        id : "category5",
        icon : "fa fa-home",
        name : "Task"
    }
    ]
  }
}
