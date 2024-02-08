import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { ClientesRoutingModule } from './clientes/clientes-routing.module';
import { ContatosRoutingModule } from './contatos/contatos-routing.module';
import { HomeRoutingModule } from './home/home-routing.module';
import { HomeComponent } from './home/home/home.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { PrestacaoServicosRoutingModule } from './prestacao-servicos/prestacao-servicos-routing.module';

const routes: Routes = [
  { path: 'login', component: LoginFormComponent },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: NotFoundComponent }
  //{
  //  path: '', component: HomeComponent, children: [
  //    { path: 'home', component: HomeComponent }
  //  ]
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), ContatosRoutingModule, HomeRoutingModule, ClientesRoutingModule, PrestacaoServicosRoutingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
