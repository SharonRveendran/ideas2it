import { Component, OnInit } from '@angular/core';
import { TodoService } from '../todo.service';

@Component({
  selector: 'app-subtask',
  templateUrl: './subtask.component.html',
  styleUrls: ['./subtask.component.scss']
})
export class SubtaskComponent implements OnInit {
  currentTaskName : any;
  constructor(private todoService : TodoService) { }
  
  ngOnInit(): void {
    this.currentTaskName = this.todoService.currentTask;
  }

}
