import { Routes } from '@angular/router';
import { AgendamentoComponent } from './pages/agendamento/agendamento.component';
import { IndexComponent } from './pages/index/index.component';

export const routes: Routes = [
    {
        path: "",
        component: IndexComponent
    },
    {
        path: "agendamento",
        component: AgendamentoComponent
    }
];
