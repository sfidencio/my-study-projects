import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PrestacaoServicosService } from 'src/app/prestacao-servicos.service';
import { PrestacaoServico } from '../prestacao-servicos';

@Component({
  selector: 'app-prestao-servicos-lista',
  templateUrl: './prestao-servicos-lista.component.html',
  styleUrls: ['./prestao-servicos-lista.component.css']
})
export class PrestaoServicosListaComponent implements OnInit {

  prestacaoServicos: PrestacaoServico[] = []; //inicializado array vazio.,
  errors?: String[];
  isPossuiRegistro: boolean = false;

  successExclusao: boolean = false;
  prestacaoServicoSelecionado!: PrestacaoServico;

  //Consulta
  nome!: string;
  mes!: number;
  meses?: number[] = [];

  constructor(private service: PrestacaoServicosService, private route: Router, private activeRouter: ActivatedRoute) {
    this.meses = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
  }
  ngOnInit(): void {
    //this.carregarTudo();
  }

  novaPrestacaoServicos() {
    this.route.navigate(['/nova-prestacao-servicos']);
  }

  prepararDelecao(ps: PrestacaoServico) { }

  excluir() { }


  carregarTudo() {
    this.service.getAllPrestacaoServicos().subscribe({
      next: (ps) => {
        this.errors = [];
        this.prestacaoServicos = ps;
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
    });
  }


  onSubmit() {
    this.consultar();
  }

  consultar() {

    if (!this.nome)
      this.nome = "";

    this.service.getAllPrestacaoServicosByMesENomeCliente(this.nome, this.mes).subscribe({
      next: (ps) => {
        this.errors = [];
        this.prestacaoServicos = ps;
        if (this.prestacaoServicos.length <= 0) {
          this.isPossuiRegistro = false;
        } else {
          this.isPossuiRegistro = true;
        }
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
    });
  }

}
