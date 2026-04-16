import { Routes } from '@angular/router';
import { AgendamentoComponent } from './pages/agendamento/agendamento.component';
import { IndexComponent } from './pages/index/index.component';
import { ServicosComponent } from './pages/servicos/servicos.component';
import { AgendaComponent } from './pages/agenda/agenda.component';
import { ListaComponent } from './pages/lista/lista.component';
import { MeusAgendamentosComponent } from './pages/meus-agendamentos/meus-agendamentos.component';
import { LoginComponent } from './pages/login/login.component';
import { CadastroComponent } from './pages/cadastro/cadastro.component';
import { AuthGuard } from './guards/auth.guard';
import { PerfilComponent } from './pages/perfil/perfil.component';

export const routes: Routes = [
    {
        path: "",
        component: IndexComponent
    },
    {
        path: "servicos/agendamento",
        component: AgendamentoComponent,
        canActivate: [AuthGuard]
    },
    {
        path: "servicos/meus-agendamentos",
        component: MeusAgendamentosComponent,
        canActivate: [AuthGuard]
    },
    {
        path: "agenda",
        component: AgendaComponent,
        canActivate: [AuthGuard]
    },
    {
        path: "agendamento/editar/:id",
        component: AgendamentoComponent,
        canActivate: [AuthGuard]
    },
    {
        path: "login",
        component: LoginComponent
    },
    {
        path: "cadastro",
        component: CadastroComponent
    },
    {
        path: "perfil",
        component: PerfilComponent
    }
];
