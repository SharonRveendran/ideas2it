import { AnimationStyleMetadata } from '@angular/animations';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { never } from 'rxjs';
import { SubtaskComponent } from '../subtask/subtask.component';
import { TodoModule } from '../todo.module';
import { TodoService } from '../todo.service';


@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent implements OnInit {
  //private x !:SubtaskComponent;
  constructor(private todoService : TodoService, private router:Router,private route : ActivatedRoute) {
    // this.x = new SubtaskComponent();
   }
  taskId = 1;
  taskList=[{id:'rsh',name : 'eag',subTaskList :[]}]
  newTaskName ="";
  currentCategoryName :any;
  ngOnInit(): void {
    alert(this.todoService.currentCategoryId)
    this.taskList = this.todoService.getTaskList();
    console.log(this.taskList);

    this.route.queryParams.subscribe(category => {
      // alert(category.id);
      // alert(category.name);
     
      this.todoService.currentCategoryId =category.id;
      this.todoService.currentCategoryName = category.name;
      this.todoService.currentCategoryId =category.id;
      this.currentCategoryName = category.name;
      });

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
  renderSubtask(task_Id: any, taskName: any) {
    alert(task_Id)
    alert(taskName)
    this.todoService.currentTaskId = task_Id;
    this.todoService.currentTask = taskName;
    this.router.navigate(["todo/home1"]);//, {queryParams:{id:task_Id}});
    this.todoService.subTaskContainerVisibility = true;
    
  }
}
