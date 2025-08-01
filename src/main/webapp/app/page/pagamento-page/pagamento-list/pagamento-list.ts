import {Component} from '@angular/core';
import {PagamentoService} from '../../../service/pagamento.service';
import {take} from 'rxjs';
import {CurrencyPipe, NgClass} from '@angular/common';
import {ActivatedRoute, Router, RouterOutlet} from '@angular/router';
import {FormsModule} from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {PagamentoForm} from '../pagamento-form/pagamento-form';

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
    private pagamentoService: PagamentoService,
    private modalService: NgbModal
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
    this.pagina = '0';
    this.carregarLista();
  }

  irParaForm(){
    this.modalService.open(PagamentoForm, {
      size: 'lg',
      backdrop: 'static'
    });
  }

}
