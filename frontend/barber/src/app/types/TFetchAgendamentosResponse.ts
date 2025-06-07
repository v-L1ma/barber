import { TAgendamento } from "./TAgendamentos";

export type TFetchAgendamentosResponse = {
    message:string;
    agendamentos: TAgendamento[]
  }