package com.barber.barber.application.usecases.listarAgendamentoPorData;

public class listarAgendamentoPorDataUseCase implements IListarAgendamentoPorDataUseCase {

    private final IAgendamentoService agendamentoService;

    public listarAgendamentoPorDataUseCase(IAgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    @Override
    public ListarAgendamentoResponseDTO executar(LocalDate data){

        List<Agendamento> agendamentos = agendamentoService.listarAgendamentosPorData(data);

        if (agendamentos.isEmpty()){
            throw new NenhumAgendamentoEncontradoException();
        }

        return new ListarAgendamentoResponseDTO("Listagem dos agendamentos do dia " + data + " feita com sucesso!", agendamentos);
    }

}
