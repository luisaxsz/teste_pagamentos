import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatusPagamentoService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API = "http://localhost:8080/api/status-pagamento"

  public listar(): Observable<any>{
    return this.http.get<any>(this.API);
  }

}
