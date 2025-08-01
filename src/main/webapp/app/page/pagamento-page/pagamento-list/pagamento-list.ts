import {Component} from '@angular/core';
import {PagamentoService} from '../../../service/pagamento.service';
import {take} from 'rxjs';
import {CurrencyPipe, NgClass} from '@angular/common';
import {RouterOutlet} from '@angular/router';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-pagamento-list',
  imports: [
    CurrencyPipe,
    RouterOutlet,
    NgClass,
    FormsModule
  ],
  standalone: true,
  templateUrl: './pagamento-list.html',
  styleUrl: './pagamento-list.css'
})
export class PagamentoList {

  pagamentosDatasource!: any;
  filtro: string = '';
  pagina: string = '1';
  tamanho: string = '10'


  constructor(
    private pagamentoService: PagamentoService
  ) {
    this.carregarLista();
  }

  carregarLista(){
    this.pagamentoService.listar(this.pagina, this.tamanho, this.filtro).pipe(take(1)).subscribe((data) => {
      this.pagamentosDatasource = data.content.map((item: any) => ({
        id: item.id,
        cpfCnpj: item.cpfCnpj,
        numCartao: item.numCartao,
        valor: item.valor,
        tipo: item.tipo?.nome,
        status: item.status?.nome,
        ativo: item.ativo
      }));
    })
  }

  filtrarPagamentos() {
    this.pagina = '1';
    this.carregarLista();
  }

}
