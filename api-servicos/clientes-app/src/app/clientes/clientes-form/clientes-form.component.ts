import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientesService } from 'src/app/clientes.service';
import { Cliente } from '../cliente';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {
  cliente: Cliente;
  errors?: String[];
  success: boolean = false;
  id?: number;

  constructor(private service: ClientesService, private route: Router, private activeRouter: ActivatedRoute) {
    this.cliente = new Cliente();
    //this.cliente.nome = "Fidencio"
  }

  ngOnInit(): void {
    this.activeRouter.params.subscribe(
      param => {
        this.id = param['id'];
        console.log(this.id);
        if (this.id) {
          this.buscarPorId(this.id);
        }
      }
    )
  }


  onSubmit() {
    //console.log(this.service.getCliente(this.cliente));
    /*this.service.salvar(this.cliente).subscribe(
      response => {
        this.success = true
        console.log(response);
        this.errors = [];
        //this.cliente = new Cliente();
        this.cliente = response;
        this.route.navigate(['/lista-cliente']);
      }, errorResponse => {
        this.success = false;
        this.errors = errorResponse.error.erros;
        console.info(errorResponse.error.erros)
      }
    );*/
    this.salvar();
  }


  salvar() {
    this.service.salvar(this.cliente).subscribe({
      next: (c) => {
        this.errors = [];
        this.cliente = c;
        this.route.navigate(['/lista-cliente']);
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
    })
  }


  buscarPorId(id: number) {
    this.service.getClienteById(id).subscribe({
      next: (c) => {
        this.errors = [];
        this.cliente = c;
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
    })
  }


}
