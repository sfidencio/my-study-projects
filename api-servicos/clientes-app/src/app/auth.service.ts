import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Usuario } from './login';
import { JwtHelperService } from '@auth0/angular-jwt'; //Acess somente informacoes publica do token


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  api?: string = "/api/v1/usuarios";
  JwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) { }

  salvar(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(environment.url_base + this.api, usuario);
  }

  isAutenticado(): boolean {
    //console.info('teste =>' + this.obterToken())
    const token: any = this.obterToken();
    if (token) {
      const isExpired = this.JwtHelper.isTokenExpired(token);
      return !isExpired;
    }
    return false;
  }

  obterUsuarioLogado() {
    return localStorage.getItem('usuario_logado');
  }

  obterToken() {
    const tokenString = localStorage.getItem('access_token');
    if (tokenString) {
      const token = JSON.parse(tokenString).access_token;
      return token;
    }
  }

  invalidarSessao() {
    localStorage.removeItem('access_token');
  }

  tentarLogar(username: string, password: string): Observable<any> {
    const paramsApp = new HttpParams()
      .set("username", username)
      .set("password", password)
      .set("grant_type", "password");

    //19 From Angular 12 btoa() and atob() functions are deprecated. Use these instead:
    //https://stackoverflow.com/questions/41972330/base-64-encode-and-decode-a-string-in-angular-2
    /*
    console.log(Buffer.from("Hello World").toString('base64'));
    // SGVsbG8gV29ybGQ=
    console.log(Buffer.from("SGVsbG8gV29ybGQ=", 'base64').toString('binary'))
    // Hello World
    */
    //let teste = Buffer.from(`${environment.clientId}:${environment.clientSecret}`).toString('base64')

    const headersApp = {
      'Authorization': 'Basic ' + btoa(`${environment.clientId}:${environment.clientSecret}`),
      'Content-Type': 'application/x-www-form-urlencoded'
    }
    return this.http.post<any>(environment.url_base + environment.tokenURL, paramsApp.toString(), { headers: headersApp })
  }
}
