import { Component } from '@angular/core';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-pagamento-list',
  imports: [
    RouterOutlet
  ],
  standalone: true,
  templateUrl: './pagamento-list.html',
  styleUrl: './pagamento-list.css'
})
export class PagamentoList {

}
