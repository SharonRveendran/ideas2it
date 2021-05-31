import { AnimationStyleMetadata } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TodoService } from '../todo.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent implements OnInit {

  constructor(private todoService : TodoService, private router:Router) { }
  taskId = 1;
  taskList : any; 
  newTaskName ="";
  currentCategoryName :any;
  ngOnInit(): void {
    this.taskList = this.todoService.getTaskList();
    this.currentCategoryName = this.todoService.currentCategoryName;
  }
  addTask() {if("" !=  this.newTaskName) {
    const obj = {
      id: this.todoService.currentCategoryId + "task" + this.taskId++,
      name: this.newTaskName,
      subTaskList : []
    }
    this.taskList.push(obj);
    this.newTaskName = "";}
  }
  renderSubtask(taskId: any, taskName: any) {
    this.todoService.currentTaskId = taskId;
    this.todoService.currentTask = taskName;
    //this.router.navigate(["todo/home"]);
    //this.todoService.subTaskContainerVisibility = true;
  }
}
