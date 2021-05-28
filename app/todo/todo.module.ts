import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { CategoryComponent } from './category/category.component';
import { TodoRoutingModule } from './todo-routing.module';
import { FormsModule } from '@angular/forms';
import { ToDoComponent } from './todo.component';

@NgModule({
  declarations: [
    HeaderComponent,
    CategoryComponent, 
    ToDoComponent
  ],
  imports: [
    CommonModule,
    TodoRoutingModule,
    FormsModule
  ]
})
export class TodoModule { }
