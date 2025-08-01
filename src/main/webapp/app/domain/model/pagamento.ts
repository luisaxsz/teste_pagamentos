import {StatusPagamento} from './status-pagamento';
import {TipoPagamento} from './tipo-pagamento';

export class Pagamento {
  id?: number;
  cpfCnpj?: string;
  tipo?: TipoPagamento;
  numCartao?: string;
  valor?: number;
  status?: StatusPagamento;
  ativo?: boolean;
}
