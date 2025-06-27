package com.barber.barber.application.usecases.atualizarAgendamento;

public class atualizarAgendamentoUseCase implements IAtualizarAgendamentoUseCase{

    private final IAgendamentoService agendamentoService;

    public atualizarAgendamentoUseCase(IAgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    @Override
    public CadastrarAgendamentoResponseDto executar(int id, Agendamento agendamentoNovo){
        Agendamento agendamento = agendamentoService.listarAgendamentoPorId(id);

        if(agendamento == null){
            throw new AgendamentoNaoEncontradoException();
        }

        /*implementar logicas:
            -agendamento não pode ser no passado
            -validar se ja existe um agendamento nessa data
        */

        agendamentoService.atualizarAgendamento(id, agendamentoNovo);

        return new CadastrarAgendamentoResponseDto("Agendamento atualizado com sucesso.");
    }

}
