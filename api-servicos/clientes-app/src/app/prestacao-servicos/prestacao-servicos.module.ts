import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrestacaoServicosRoutingModule } from './prestacao-servicos-routing.module';
import { PrestaoServicosListaComponent } from './prestao-servicos-lista/prestao-servicos-lista.component';
import { PrestaoServicosFormComponent } from './prestao-servicos-form/prestao-servicos-form.component';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { IConfig, NgxMaskModule } from 'ngx-mask';
import { HomeModule } from '../home/home.module';

const maskConfig: Partial<IConfig> = {
  validation: false,
};


@NgModule({
  declarations: [
    PrestaoServicosListaComponent,
    PrestaoServicosFormComponent
  ],
  imports: [
    CommonModule,
    PrestacaoServicosRoutingModule,
    CommonModule,
    BrowserModule,
    FormsModule,
    HomeModule,
    NgxMaskModule.forRoot(maskConfig)
  ],exports: [
    PrestaoServicosListaComponent,
    PrestaoServicosFormComponent
  ]
})
export class PrestacaoServicosModule { }
