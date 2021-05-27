import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { CategoryComponent } from "./category/category.component";
import { HeaderComponent } from "./header/header.component";

const routes: Routes = [
  { 
    path: 'category', component: CategoryComponent 
  },
  { 
    path: 'header', component: HeaderComponent 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TodoRoutingModule { }
RouterModule.forChild([])