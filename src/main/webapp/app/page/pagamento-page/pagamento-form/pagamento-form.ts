import {Component} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TipoPagamentoService} from '../../../service/tipo-pagamento.service';
import {take} from 'rxjs';
import {TipoPagamento} from '../../../domain/model/tipo-pagamento';
import {PagamentoService} from '../../../service/pagamento.service';
import {NgClass} from '@angular/common';
import {StatusPagamento} from '../../../domain/model/status-pagamento';
import {StatusPagamentoService} from '../../../service/status-pagamento.service';
import {StatusPagamentoTypeEnum} from '../../../domain/enums/status-pagamento-type.enum';
import {TipoPagamentoTypeEnum} from '../../../domain/enums/tipo-pagamento-type.enum';

@Component({
  selector: 'app-pagamento-form',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgClass
  ],
  templateUrl: './pagamento-form.html',
  styleUrl: './pagamento-form.css'
})
export class PagamentoForm {

  protected readonly formGroup: FormGroup;

  tipoPagamentoDatasource!: TipoPagamento[];
  statusPagamentoDatasource!: StatusPagamento[];


  mostrarToast: boolean = false;
  mensagemToast: string = '';
  classeToast: string = 'text-bg-primary';

  isPagamentoCartao: boolean = false;

  constructor(
    private fb: FormBuilder,
    private tipoPagamentoService: TipoPagamentoService,
    private statusPagamentoService: StatusPagamentoService,
    private pagamentoService: PagamentoService
  ) {
    this.getTiposPagamento();
    this.getStatusPagamento();

    this.formGroup = this.fb.group({
      ativo: [true],
      cpfCnpj: [null],
      numCartao: [null],
      status: this.fb.group({
        id: [null],
        tipo: [null],
        nome: [null]
      }),
      tipo: this.fb.group({
        id: [null],
        tipo: [null],
        nome: [null]
      }),
      valor: [null]
    })

    this.formGroup?.get("tipo.tipo")?.valueChanges.subscribe((result: TipoPagamentoTypeEnum) => {
      this.isPagamentoCartao = result === TipoPagamentoTypeEnum.CARTAO_CREDITO
    })
  }


  getTiposPagamento() {
    this.tipoPagamentoService.listar().pipe(take(1)).subscribe((data) => {
      this.tipoPagamentoDatasource = data;
    })
  }

  getStatusPagamento() {
    this.statusPagamentoService.listar().pipe(take(1)).subscribe((data) => {
      this.statusPagamentoDatasource = data;
      this.definirStatusPagamento(data);
    })
  }

  definirStatusPagamento(statusPagamentoDatasource: StatusPagamento[]) {
    const statusPadrao = statusPagamentoDatasource.find(item => item.tipo == StatusPagamentoTypeEnum.PENDENTE_PROCESSAMENTO)
    if (statusPadrao) {
      this.formGroup.patchValue({
        status: {
          id: statusPadrao.id,
          nome: statusPadrao.nome,
          tipo: statusPadrao.tipo
        }
      });
    }
  }

  save() {
    console.log(this.formGroup.value)
    if (this.formGroup.valid) {
      this.pagamentoService.adicionar(this.formGroup.value).subscribe({
        next: () => {
          this.showToastMessage('Pagamento salvo com sucesso!', 'text-bg-success');
          this.formGroup.reset();
        },
        error: () => {
          this.showToastMessage('Erro ao salvar pagamento.', 'text-bg-danger');
        }
      });
    } else {
      this.showToastMessage('Preencha todos os campos obrigatórios.', 'text-bg-warning');
    }
  }

  showToastMessage(message: string, cssClass: string) {
    this.mensagemToast = message;
    this.classeToast = cssClass;
    this.mostrarToast = true;

    setTimeout(() => this.mostrarToast = false, 3000);
  }

}
