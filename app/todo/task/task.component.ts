import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TodoService } from '../todo.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})

export class TaskComponent implements OnInit {
  constructor(private todoService: TodoService, private router: Router, private route: ActivatedRoute) { }
  taskId = 1;
  taskList : any
  newTaskName = "";
  currentCategoryName: any = '';

  ngOnInit(): void {
    this.taskList = this.todoService.getTaskList();
    console.log(this.taskList);
    this.route.queryParams.subscribe(category => {
      this.taskList = this.todoService.getTaskList();
      this.todoService.currentCategoryId = category.id;
      this.todoService.currentCategoryName = category.name;
      this.currentCategoryName = category.name;
    });
  }

  /**
   * Method to add task
   */
  addTask() {
    if ("" != this.newTaskName) {
      const obj = {
        id: this.todoService.currentCategoryId + "task" + this.taskId++,
        name: this.newTaskName,
        subTaskList: []
      }
      this.todoService.addTask(obj)
      console.log(this.todoService.categoryList);
      this.newTaskName = "";
      this.taskList = this.todoService.getTaskList();
    }
  }

  /**
   * Method to render subTask container
   * @param task_Id task id
   * @param taskName  task name
   */
  renderSubtask(task_Id: any, taskName: any) {
    this.todoService.currentTaskId = task_Id;
    this.todoService.currentTask = taskName;
    this.todoService.subTaskContainerVisibility = true;
    this.currentCategoryName = this.todoService.getCategoryName(this.todoService.currentCategoryId);
    this.router.navigate(["todo/home1"], {
      queryParams: {
        name: this.currentCategoryName,
        id: this.todoService.currentCategoryId, task_id: task_Id, taskName: taskName
      }
    });
  }
}
