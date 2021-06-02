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
  currentTaskId: any
  currentTaskName: any;
  currentSubTaskName: any;
  currentCategoryId: any
  subTaskList: {
    id: string;
    name: string;
  }[] = [];

  constructor(private route: ActivatedRoute, private todoService: TodoService) {
  }

  ngOnInit(): void {
    this.currentTaskName = this.todoService.currentTask;
    this.route.queryParams.subscribe(data => {
      this.currentTaskName = data.taskName;
      this.subTaskList = this.todoService.getSubTaskList(data.task_id, data.id);
      this.currentTaskId = data.task_id;
      this.currentCategoryId = data.id
    });
  }

  /**
   * Method to add subtask
   */
  addSubTask() {
    const obj = {
      id: this.todoService.currentTaskId + this.subTaskId++,
      name: this.currentSubTaskName,
    }
    this.currentSubTaskName = '';
    this.todoService.addSubTask(obj, this.currentTaskId, this.currentCategoryId);
  }
}
