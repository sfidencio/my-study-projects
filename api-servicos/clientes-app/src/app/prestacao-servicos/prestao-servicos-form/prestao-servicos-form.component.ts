import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientesService } from 'src/app/clientes.service';
import { Cliente } from 'src/app/clientes/cliente';
import { PrestacaoServicosService } from 'src/app/prestacao-servicos.service';
import { PrestacaoServico } from '../prestacao-servicos';

@Component({
  selector: 'app-prestao-servicos-form',
  templateUrl: './prestao-servicos-form.component.html',
  styleUrls: ['./prestao-servicos-form.component.css']
})
export class PrestaoServicosFormComponent implements OnInit {

  clientes: Cliente[] = [];
  errors?: String[];
  prestacaoServico: PrestacaoServico = new PrestacaoServico();
  success: boolean = false;
  id?: number;

  constructor(private service: PrestacaoServicosService, private serviceCliente: ClientesService, private route: Router, private activeRouter: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.carregarClientes();
  }




  onSubmit() {
    this.salvar();
  }

  salvar() {
    console.info(this.prestacaoServico.cliente?.nome + " - " + this.prestacaoServico.cliente?.id)
    console.warn(this.prestacaoServico);

    this.service.salvar(this.prestacaoServico).subscribe({
      next: (ps) => {
        this.errors = [];
        this.prestacaoServico = ps;
        this.route.navigate(['/lista-prestacao-servicos']);
        console.log(ps)
      },
      error: (e) => {
        this.errors = e.error.erros;
        console.info(e.error.erros)
        //console.error(e)
      },
      complete: () => {
        console.info('complete')
      }
    })
  }


  carregarClientes() {
    this.serviceCliente.getAllClientes().subscribe({
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
}
