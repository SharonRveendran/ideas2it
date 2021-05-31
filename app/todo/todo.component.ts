import { Component, OnInit } from '@angular/core';
import { TodoService } from './todo.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.scss']
})
export class ToDoComponent implements OnInit {
  constructor(private todoService: TodoService) { }
  taskContainerVisibility : any;
  subTaskContainerVisibility : any;
  ngOnInit(): void {
    this.taskContainerVisibility = this.todoService.taskContainerVisibility;
    this.subTaskContainerVisibility =  this.todoService.subTaskContainerVisibility;
  }
}