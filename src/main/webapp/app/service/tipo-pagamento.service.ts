import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {map, Observable} from 'rxjs';
import {TipoPagamento} from '../domain/model/tipo-pagamento';

@Injectable({
  providedIn: 'root'
})
export class TipoPagamentoService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API = "http://localhost:8080/api/tipo-pagamento"

  public listar(): Observable<any>{
    return this.http.get<any>(this.API);
  }
}
