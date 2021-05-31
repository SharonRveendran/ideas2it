import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { TaskComponent } from "./task/task.component";
import { ToDoComponent } from "./todo.component";

const routes: Routes = [
  { 
    path: 'home', component: ToDoComponent  
  },
  {
    path: 'task', component: TaskComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TodoRoutingModule { }
RouterModule.forChild([])