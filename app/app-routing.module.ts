import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';
import { TodoModule } from './todo/todo.module';

const routes: Routes = [
  { path: '', component: SignInComponent 
  },
  {
    path: "todo", loadChildren: () => TodoModule
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
RouterModule.forRoot([])