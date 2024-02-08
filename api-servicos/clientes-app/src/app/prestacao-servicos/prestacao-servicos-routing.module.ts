import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../auth.guard';
import { PrestaoServicosFormComponent } from './prestao-servicos-form/prestao-servicos-form.component';
import { PrestaoServicosListaComponent } from './prestao-servicos-lista/prestao-servicos-lista.component';

const routes: Routes = [
  { path: 'nova-prestacao-servicos', component: PrestaoServicosFormComponent, canActivate: [AuthGuard] },
  { path: 'lista-prestacao-servicos', component: PrestaoServicosListaComponent, canActivate: [AuthGuard] },
  { path: 'edit-prestacao-servicos/:id', component: PrestaoServicosFormComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrestacaoServicosRoutingModule { }
