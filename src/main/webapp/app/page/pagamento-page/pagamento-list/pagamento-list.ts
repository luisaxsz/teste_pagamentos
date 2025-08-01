import {Component} from '@angular/core';
import {PagamentoService} from '../../../service/pagamento.service';
import {take} from 'rxjs';
import {CurrencyPipe, NgClass} from '@angular/common';
import {ActivatedRoute, Router, RouterOutlet} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {PagamentoForm} from '../pagamento-form/pagamento-form';
import {StatusPagamentoService} from '../../../service/status-pagamento.service';
import {StatusPagamento} from '../../../domain/model/status-pagamento';
import {StatusPagamentoTypeEnum} from '../../../domain/enums/status-pagamento-type.enum';

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
  statusPagamentoDatasource!: StatusPagamento[];

  filtro: string = '';
  pagina: string = '1';
  tamanho: string = '10'

  isFiltered: boolean = false


  constructor(
    private pagamentoService: PagamentoService,
    private statusPagamentoService: StatusPagamentoService,
    private modalService: NgbModal
  ) {
    this.carregarLista();
    this.carregarStatusPagamento();
  }

  carregarListaPage() {
    this.pagamentoService.listarPage(this.pagina, this.tamanho, this.filtro).pipe(take(1)).subscribe((data) => {
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

  carregarLista() {
    this.pagamentoService.listar().pipe(take(1)).subscribe((data) => {
      this.pagamentosDatasource = data.map((item: any) => ({
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

  carregarStatusPagamento() {
    this.statusPagamentoService.listar().pipe(take(1)).subscribe((data) => {
      this.statusPagamentoDatasource = data;
    })
  }

  filtrarByStatus(status?: string) {
    this.pagamentoService.filtrarByTypo(status).subscribe((data) => {
      this.isFiltered = true
      return this.pagamentosDatasource = data.map((item: any) => ({
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

  limparFiltroStatus() {
    this.isFiltered = false;
    this.carregarLista();
  }

  filtrarPagamentos() {
    this.pagina = '0';
    this.carregarLista();
  }

  irParaForm() {
    this.modalService.open(PagamentoForm, {
      size: 'lg',
      backdrop: 'static'
    });
  }

}
