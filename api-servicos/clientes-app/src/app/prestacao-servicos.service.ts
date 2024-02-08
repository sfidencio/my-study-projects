import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cliente } from './clientes/cliente';
import { PrestacaoServico } from './prestacao-servicos/prestacao-servicos';

@Injectable({
  providedIn: 'root'
})
export class PrestacaoServicosService {

  api?: string = "/api/v1/prestacao-servicos";

  constructor(private http: HttpClient) { }

  salvar(prestacaoServico: PrestacaoServico): Observable<PrestacaoServico> {
    if (prestacaoServico.id) {
      return this.http.put<PrestacaoServico>(environment.url_base + this.api + "/" + `${prestacaoServico.id}`, prestacaoServico);
    } else {
      return this.http.post<PrestacaoServico>(environment.url_base + this.api, prestacaoServico);
    }
  }

  deletePrestacaoServicosById(id: string): Observable<any> {
    return this.http.delete<any>(environment.url_base + this.api + "/" + `${id}`)
  }

  getAllPrestacaoServicos(): Observable<PrestacaoServico[]> {
    return this.http.get<PrestacaoServico[]>(environment.url_base + this.api);
  }

  getAllPrestacaoServicosByMesENomeCliente(nome: string, mes: number): Observable<PrestacaoServico[]> {
    const params = new HttpParams()
      .set("nome", nome)
      .set("mes", mes)
    return this.http.get<PrestacaoServico[]>(environment.url_base + this.api + "/filtro", { params });
  }

  getPrestacaoServicosById(id: Number): Observable<PrestacaoServico> {
    return this.http.get<PrestacaoServico>(environment.url_base + this.api + "/" + `${id}`)
  }

}
