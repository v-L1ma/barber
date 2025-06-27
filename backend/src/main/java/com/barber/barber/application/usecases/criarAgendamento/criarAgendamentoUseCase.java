package com.barber.barber.application.usecases.criarAgendamento;

public class criarAgendamentoUseCase implements ICriarAgendamentoUseCase {

     public CadastrarAgendamentoResponseDto executar(CadastrarAgendamentoDto agendamentoDto){

        if (agendamentoDto.cliente() == null ||
                agendamentoDto.data() == null ||
                agendamentoDto.horario() == null||
                agendamentoDto.servico() == null){
            throw new CamposObrigatoriosException();
        }

        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();

        for (Agendamento item : agendamentos){
            if (item.getData().equals(agendamentoDto.data()) && item.getHorario().equals(agendamentoDto.horario())){

                CadastrarAgendamentoResponseDto response = new CadastrarAgendamentoResponseDto("Já existe um agendamento nessa data e nesse horário.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        }

        agendamentoService.inserirAgendamento(agendamentoDto);
        
        return new CadastrarAgendamentoResponseDto("Agendamento feito com sucesso");
    
     }

}
