import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  constructor() { }
  taskContainerVisibility = false;
  currentCategoryName:any;
  currentCategoryId !: string;
  currentTaskList :any;
  currentTask = "temp";//:any;
  currentTaskId :any;
  subTaskContainerVisibility = false;
  currentSubTaskList:{
    id: string;
    name: string;
  }[] = [];


  allSubTaskList: {
    id: string;
    name: string;
  }[]=[];


  categoryList =[
    {
      id : "category1",
      icon : "fa fa-home",//sun
      name : "My Day",
      taskList :[]
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
    alert(this.currentCategoryId);
    for(const obj of this.categoryList) {
      if(obj.id == this.currentCategoryId) {
        this.currentTaskList = obj.taskList;
      }
    }
    return this.currentTaskList;
    //return [{id: "ergergh",name : 'wedjnr',subTaskList :[]}]
  }
  getSubTaskList() { 
// let x:any;


    // let subTaskList: {
    //   id: string;
    //   name: string;
    // }[] = [];



    // for(let obj of this.categoryList) {
    //   console.log("111111")
    //   console.log(obj.taskList);
    //   x = obj.taskList;
      // for(const tslist of obj.taskList) {
      //    console.log(tslist);
      //    console.log("22222222")
      //    //subTaskList = tslist.subTaskList;
      // }
      this.allSubTaskList.push({
        id :"earg",
        name :"wrg"
      })
      return this.allSubTaskList;
    }


// return [{
//   id: 'mu id',
//   name: 'my name'
// }]
// return subTaskList;




    // console.log("cat list   ="+this.categoryList+"\n currnt tasklist   ="+ 
    // this.currentTaskList+"\ncurrent category id"+this.currentCategoryId);
    // this.currentTaskList = this.getTaskList() ;
    // //this.getTaskList()
    // for(const obj of this.categoryList) {
    //   if(obj.id == 'category1') {
    //     this.currentTaskList = obj.taskList;
    //   }
    // }
    // for(const taskList of this.currentTaskList) {
    //   if(this.currentTask == taskList.name) {
    //     this.currentSubTaskList = taskList.subTaskList;
    //   }
    // }
    // return this.currentSubTaskList;
  
}
