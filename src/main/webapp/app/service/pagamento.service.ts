import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PagamentoService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API = "http://localhost:8080/api/pagamentos"

  public listar(pagina: string, qntItens: string, q?: string): Observable<any>{
    let params = new HttpParams()
      .set('pagina', pagina)
      .set('tamanho', qntItens);

    if (q && q.trim() !== '') {
      params = params.set('q', q.trim());
    }

    return this.http.get(this.API, { params })
  }

  public processar(){ }

  public inativar(){ }

  public adicionar() { }


}
