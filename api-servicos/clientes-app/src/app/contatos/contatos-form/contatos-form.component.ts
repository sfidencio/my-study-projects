import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { ContatosService } from 'src/app/contatos.service';
import { Contato } from '../contato';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms'
import { MatSnackBar } from '@angular/material/snack-bar';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-contatos-form',
  templateUrl: './contatos-form.component.html',
  styleUrls: ['./contatos-form.component.css']
})
//https://www.itsolutionstuff.com/post/angular-14-reactive-forms-validation-exampleexample.html
export class ContatosFormComponent implements OnInit {


  contato?: Contato;
  contatos: Contato[] = [];
  errors!: String[];
  success: boolean = false;
  id?: number;
  formulario!: FormGroup;
  colunas: string[] = ['foto', 'id', 'nome', 'email', 'favorito'];
  totalElementos = 0;
  pagina = 0;
  tamanho = 2;
  pageSizeOptions: number[] = [2];




  constructor(private service: ContatosService,
    private route: Router, private activeRouter: ActivatedRoute,
    private fb: FormBuilder,
    private snackBar: MatSnackBar) {
    //this.contato = new Contato();
    //this.cliente.nome = "Fidencio"
  }

  ngOnInit(): void {
    this.formulario = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.email, Validators.email]]
    })

    /*this.formulario = new FormGroup({
      nome: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required)
    });*/

    this.carregar(this.pagina, this.tamanho);

  }

  public get f() { return this.formulario.controls; }

  onSubmit() {
    //console.log(this.formulario?.value)
    //console.log(this.formulario.valid)
    const formValues = this.formulario.value;
    this.contato = new Contato();
    this.contato.nome = formValues.nome;
    this.contato.email = formValues.email;
    console.log(this.contato);


    this.service.salvar(this.contato).subscribe({
      next: (c) => {
        this.errors = [];
        this.contato = c;
        this.route.navigate(['/contatos']);
        console.log(c)
        this.carregar(this.pagina, this.tamanho);
        this.snackBar.open('Contato adicionado!', 'sucesso', {
          duration: 2000
        })
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

    //this.formulario.controls.email.errors.email

  }


  /*carregar() {
    this.service.getAllContatos().subscribe({
      next: (c) => {
        this.errors = [];
        this.contatos = c;
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
  }*/



  carregar(pagina: number, tamanho: number) {
    this.service.getAllContatosPaginado(pagina.toString(), tamanho.toString()).subscribe({
      next: (c) => {
        this.errors = [];
        this.contatos = c.content;
        this.totalElementos = c.totalElements;
        this.pagina = c.number;
        console.log(c.content)
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

  favoritar(contato: Contato, event: Event) {
    event.preventDefault();
    this.service.favoritar(contato!.id).subscribe({
      next: (c) => {
        this.errors = [];
        this.contato = c;
        this.route.navigate(['/contatos']);
        //contato!.favorito = !contato.favorito;
        console.log(c)
        this.carregar(this.pagina, this.tamanho);
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

  upload(event: any, contato: Contato) {
    const files = event.target.files;
    //console.info(files)
    if (files) {
      const foto = files[0];
      const formData: FormData = new FormData();
      formData.append("foto", foto);
      this.service.adicionarFoto(contato.id, formData).subscribe({
        next: (c) => {
          this.errors = [];
          //this.contato = c;
          //this.carregar();
          console.log(c)
          //this.route.navigate(['/']);
          //contato!.favorito = !contato.favorito;
          //console.log(c)
          //window.location.reload();
        },
        error: (e) => {
          this.errors = e.error.erros;
          this.route.navigate(['/contatos']);
          window.location.reload();
          //console.info(e.error.erros)
          //console.error(e)
        },
        complete: () => {
          console.info('complete')
        }
      })
    }

  }


  paginar(event: PageEvent) {
    this.pagina = event.pageIndex;
    this.carregar(this.pagina, this.tamanho);
  }

}
