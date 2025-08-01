import {RouterModule, Routes} from '@angular/router';
import { PagamentoForm } from './page/pagamento-page/pagamento-form/pagamento-form';
import { PagamentoList } from './page/pagamento-page/pagamento-list/pagamento-list';
import { PagamentoPage } from './page/pagamento-page/pagamento-page';
import {NgModule} from '@angular/core';

export const routes: Routes = [
  {
    path: "",
    component: PagamentoPage,
    children: [
      {
        path: "",
        redirectTo: "listar-pagamentos",
        pathMatch: "full"
      },
      {
        path: "listar-pagamentos",
        component: PagamentoList
      },
      {
        path: "pagamento-form",
        component: PagamentoForm
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
