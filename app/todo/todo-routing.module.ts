import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SubtaskComponent } from "./subtask/subtask.component";
import { TaskComponent } from "./task/task.component";
import { ToDoComponent } from "./todo.component";

const routes: Routes = [
  { 
    path: 'home', component: ToDoComponent  
    // children : [
    //   {
    //     path: 'task', component: TaskComponent
    //   }
    // ]
  },
  {
    path: 'home1', component: ToDoComponent   
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TodoRoutingModule { }
RouterModule.forChild([])