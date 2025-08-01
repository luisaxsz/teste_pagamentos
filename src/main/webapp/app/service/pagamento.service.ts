import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PagamentoService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API = "http://localhost:8080/api/pagamentos"


}
