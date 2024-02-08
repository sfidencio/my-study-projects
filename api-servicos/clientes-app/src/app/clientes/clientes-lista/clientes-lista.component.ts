import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClientesService } from 'src/app/clientes.service';
import { Cliente } from '../cliente';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {

  clientes: Cliente[] = []; //inicializado array vazio.,
  errors?: String[];
  successExclusao: boolean = false;
  clienteSelecionado!: Cliente;

  constructor(private service: ClientesService, private route: Router) { }

  ngOnInit(): void {
    //this.clientes = this.service.getAllClientes2();
    /*this.service.getAllClientes().subscribe(
      response => {
        //this.success = true
        console.log(response);
        this.errors = [];
        //this.cliente = new Cliente();
        this.clientes = response;
      }, errorResponse => {
        //this.success = false;
        this.errors = errorResponse.error.erros;
        console.info(errorResponse.error.erros)
      }
    );*/
    this.clienteSelecionado = new Cliente();
    this.carregar();
  }
  
  carregar() {
    this.service.getAllClientes().subscribe({
      next: (c) => {
        this.errors = [];
        this.clientes = c;
        console.log(c)
      },
      error: (e) => {
        this.errors = e.error.erros;
        console.info(e.error.erros)
        //console.error(e)
      },
      complete: () => {
        console.info('complete')
      }
    });
  }

  novoCliente() {
    this.route.navigate(['/novo-cliente']);
  }

  prepararDelecao(cliente: Cliente) {
    this.clienteSelecionado = cliente;
  }

  excluir() {
    this.service.deleteClienteById(String(this.clienteSelecionado.id)).subscribe({
      next: (c) => {
        this.errors = [];
        this.route.navigate(['/lista-cliente']);
        this.carregar();
        console.log(c)
        this.successExclusao =true;
      },
      error: (e) => {
        this.successExclusao = false;
        this.errors = e.error.erros;
        console.info(e.error.erros)
        //console.error(e)
      },
      complete: () => {
        console.info('complete')
      }
    });
  }
}
