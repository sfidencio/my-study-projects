import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ContatosFormComponent } from './contatos-form/contatos-form.component';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatToolbarModule } from '@angular/material/toolbar';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserModule } from '@angular/platform-browser';
import { IConfig, NgxMaskModule } from 'ngx-mask';
import { ClientesRoutingModule } from '../clientes/clientes-routing.module';
import { HomeModule } from '../home/home.module';

const maskConfig: Partial<IConfig> = {
  validation: false,
};


@NgModule({
  declarations: [
    ContatosFormComponent
  ],
  imports: [
    CommonModule,
    ClientesRoutingModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HomeModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatIconModule,
    MatInputModule,
    MatSnackBarModule,
    MatTableModule,
    MatTabsModule,
    MatCardModule,
    MatPaginatorModule,
    NgxMaskModule.forRoot(maskConfig)
  ], exports: [
    ContatosFormComponent
  ]
})
export class ContatosModule { }
