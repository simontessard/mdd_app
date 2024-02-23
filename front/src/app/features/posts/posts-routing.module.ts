import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailComponent } from './components/detail/detail.component';
import { FormComponent } from './components/form/form.component';
import { ListComponent } from './components/list/list.component';

const routes: Routes = [
  { path: '', title: 'Posts', component: ListComponent },
  { path: 'detail/:id', title: 'Posts - detail', component: DetailComponent },
  { path: 'create', title: 'Posts - create', component: FormComponent },
  { path: 'update/:id', title: 'Posts - update', component: FormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostsRoutingModule {
}
