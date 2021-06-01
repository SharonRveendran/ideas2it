import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TodoService } from '../todo.service';

@Component({
  selector: 'app-subtask',
  templateUrl: './subtask.component.html',
  styleUrls: ['./subtask.component.scss']
})
export class SubtaskComponent implements OnInit {
  subTaskId = 1;
  currentTaskName : any;
  currentSubTaskName :any;
  subTaskList: {
    id: string;
    name: string;
  }[] = [];
  private todoService !:TodoService;
  constructor(private route: ActivatedRoute) { 
    this.todoService = new TodoService();
  }
  ngOnInit(): void {
    this.currentTaskName = this.todoService.currentTask;
    this.subTaskList = this.todoService.getSubTaskList();
    // alert("sub task on init");
    // this.route.queryParams.subscribe(data => {
    //   this.subTaskList = this.todoService.getSubTaskList(data.id);
    //    });
  }
  addSubTask() {
    const obj = {
      id: this.todoService.currentTaskId + this.subTaskId++,
      name: this.currentSubTaskName,
    }
    this.subTaskList.push(obj);//this.todoService.allSubTaskList.push(obj);
    this.currentSubTaskName = '';
  }
  
}
