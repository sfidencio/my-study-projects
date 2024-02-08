import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Usuario } from '../login';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  login!: string;
  senha!: string;
  //loginError: boolean = false;
  cadastrando: boolean = false;
  usuario?: Usuario;
  errors?: string[];
  mensagem?: string;



  constructor(private route: Router, private auth: AuthService) { }

  ngOnInit(): void {
    if (this.auth.isAutenticado())
      this.route.navigate(['/home']);
  }

  onSubmit() {
    console.log(`Usuario: ${this.login}, Senha: ${this.senha}`)
    this.efetuarLogin();
  }

  prepararCadastro(event: any) {
    //event.preventDefault;
    this.cadastrando = true;
    this.errors = [];
    this.mensagem = "";
    this.login = "";
    this.senha = "";
  }

  cancelar(event: any) {
    //event.preventDefault;
    this.cadastrando = false;
    this.errors = [];
    this.mensagem = "";
    this.login = "";
    this.senha = "";
  }

  efetuarLogin() {
    this.auth.tentarLogar(this.login, this.senha).subscribe({
      next: (c) => {
        this.errors = [];
        //this.usuario = c;
        //this.loginError = false;
        //this.route.navigate(['/lista-cliente']);
        console.log(c)
        this.cadastrando = false;
        this.mensagem = `Usuário ${this.login} logado com sucesso!`;
        //this.mensagem = "Usuário logado com sucesso!";
        localStorage.setItem('access_token', JSON.stringify(c));
        localStorage.setItem('usuario_logado', this.login)
        if (c)
          this.route.navigate(['/']);
        this.login = "";
        this.senha = "";

      },
      error: (e) => {
        //this.loginError = true;
        this.mensagem = "";
        this.errors = e.error.erros;
        if (e.status == 400)
          this.errors = ['Credenciais de acesso inválidas!'];
        if (e.status == 401)
          this.errors = ['Acesso não autorizado!'];
        console.info(e.error.erros);
        this.cadastrando = false;
        //console.error(e)
        this.auth.invalidarSessao();
      },
      complete: () => {
        console.info('complete')
        this.cadastrando = false;
      }
    })
  }

  cadastrar() {
    this.usuario = new Usuario();
    this.usuario.login = this.login;
    this.usuario.senha = this.senha;
    this.auth.salvar(this.usuario).subscribe({
      next: (c) => {
        this.errors = [];
        this.mensagem = `Usuário ${c.login} cadastrado com sucesso!`;
        this.usuario = c;
        //this.loginError = false;
        //this.route.navigate(['/lista-cliente']);
        console.log(c)
        this.cadastrando = false;
        this.login = "";
        this.senha = "";

      },
      error: (e) => {
        //this.loginError = true;
        this.mensagem = "";
        this.errors = e.error.erros;
        console.info(e.error.erros)
        this.cadastrando = true;
        //console.error(e)
      },
      complete: () => {
        console.info('complete')
        this.cadastrando = false;
      }
    })
  }

}
