import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Contato } from './contatos/contato';
import { ContatoPaginado } from './contatos/contato-paginado';


@Injectable({
  providedIn: 'root'
})
export class ContatosService {

  constructor(private http: HttpClient) { }
  api?: string = "/api/v1/contatos";

  salvar(contato: Contato): Observable<Contato> {

    if (contato.id) {
      return this.http.put<Contato>(environment.url_base + this.api + "/" + `${contato.id}`, contato);
    } else {
      return this.http.post<Contato>(environment.url_base + this.api, contato);
    }
  }

  getAllContatos(): Observable<Contato[]> {
    return this.http.get<Contato[]>(environment.url_base + this.api);
  }


  getAllContatosPaginado(pagina: string, tamanho: string): Observable<ContatoPaginado> {
    return this.http.get<ContatoPaginado>(environment.url_base + this.api + "?pagina=" + pagina + "&tamanho=" + tamanho);
  }

  favoritar(id: string): Observable<Contato> {
    return this.http.patch<Contato>(environment.url_base + this.api + "/" + `${id}` + "/favorito", null);
  }
  adicionarFoto(id: string, formData: FormData): Observable<any> {
    return this.http.patch<any>(environment.url_base + this.api + "/" + `${id}` + "/foto", formData);
  }

}
