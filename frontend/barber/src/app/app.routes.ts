import { Routes } from '@angular/router';
import { AgendamentoComponent } from './pages/agendamento/agendamento.component';
import { IndexComponent } from './pages/index/index.component';
import { ServicosComponent } from './pages/servicos/servicos.component';
import { AgendaComponent } from './pages/agenda/agenda.component';
import { ListaComponent } from './pages/lista/lista.component';
import { MeusAgendamentosComponent } from './pages/meus-agendamentos/meus-agendamentos.component';

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
        component: ServicosComponent,
        children:[
            {
                path:"lista",
                component: ListaComponent
            },
            {
                path:"meus-agendamentos",
                component: MeusAgendamentosComponent
            }
        ]
    },
    {
        path: "agenda",
        component: AgendaComponent
    },
    {
        path: "agendamento/editar/:id",
        component: AgendamentoComponent
    }
];
