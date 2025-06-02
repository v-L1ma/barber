import { Routes } from '@angular/router';
import { AgendamentoComponent } from './pages/agendamento/agendamento.component';
import { IndexComponent } from './pages/index/index.component';
import { ServicosComponent } from './pages/servicos/servicos.component';
import { AgendaComponent } from './pages/agenda/agenda.component';

export const routes: Routes = [
    {
        path: "",
        component: IndexComponent
    },
    {
        path: "agendamento",
        component: AgendamentoComponent
    },
    {
        path: "servicos",
        component: ServicosComponent
    },
    {
        path: "agenda",
        component: AgendaComponent
    }
];
