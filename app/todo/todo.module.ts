import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { CategoryComponent } from './category/category.component';
import { TodoRoutingModule } from './todo-routing.module';

@NgModule({
  declarations: [
    HeaderComponent,
    CategoryComponent
  ],
  imports: [
    CommonModule,
    TodoRoutingModule
  ]
})
export class TodoModule { }
