package com.barber.barber.application.usecases.deletarAgendamento;

public class deletarAgendamentoUseCase implements IDeletarAgendamentoUseCase {

    private final IAgendamentoService agendamentoService;

    public deletarAgendamentoUseCase(IAgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    @Override
    public CadastrarAgendamentoResponseDto executar(int id){
        
         Agendamento agendamento = agendamentoService.listarAgendamentoPorId(id);

        if(agendamento == null){
            throw new AgendamentoNaoEncontradoException();
        }

        agendamentoService.deletarAgendamento(id);

        return new CadastrarAgendamentoResponseDto("Agendamento excluído com sucesso");
    }

}
