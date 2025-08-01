import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Pagamento} from '../domain/model/pagamento';
import {StatusPagamentoTypeEnum} from '../domain/enums/status-pagamento-type.enum';

@Injectable({
  providedIn: 'root'
})
export class PagamentoService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API = "http://localhost:8080/api/pagamentos"

  public listar(pagina: string, qntItens: string, q?: string): Observable<any>{
    let params = new HttpParams();

    if (q && q.trim() !== '') {
      params = params.set('q', q.trim());
    }

    return this.http.get(this.API, { params })
  }

  public processar(){ }

  public inativar(){ }

  public adicionar(resource: Pagamento): Observable<Pagamento> {
    return this.http.post(this.API, resource);
  }

  public filtrarByTypo(status?: string): Observable<Pagamento[]>{
    return this.http.get<Pagamento[]>(`${this.API}/status/${status}`)
  }


}
