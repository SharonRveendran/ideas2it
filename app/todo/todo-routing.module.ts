import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ToDoComponent } from "./todo.component";

const routes: Routes = [
  { 
    path: 'home', component: ToDoComponent 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TodoRoutingModule { }
RouterModule.forChild([])