package com.barber.barber.application.usecases.atualizarDadosCadastrais;

import com.barber.barber.application.services.ClienteService.IClienteService;
import com.barber.barber.domain.entities.Cliente.Cliente;
import com.barber.barber.domain.exceptions.CamposObrigatoriosException;
import com.barber.barber.domain.exceptions.SenhaInvalidaException;
import com.barber.barber.domain.exceptions.UsuarioNaoCadastradoException;
import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;
import com.barber.barber.infra.web.DTOs.CadastrarClienteResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AtualizarDadosCadastraisUseCase implements IAtualizarDadosCadastraisUseCase {

    private final IClienteService clienteService;
    private final PasswordEncoder passwordEncoder;


    public AtualizarDadosCadastraisUseCase(IClienteService clienteService, PasswordEncoder passwordEncoder){
        this.clienteService = clienteService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CadastrarClienteResponseDto executar(int id, CadastrarClienteDto dto) {
        if (    dto.getNome() == null ||
                dto.getEmail() == null ||
                dto.getCelular() == null ||
                dto.getDataNascimento() == null){
            throw new CamposObrigatoriosException();
        }

        Cliente cliente = clienteService.buscarClientePorId(id);

        if (cliente == null){
            throw new UsuarioNaoCadastradoException("Usuário fornecido não existe.");
        }

        if(dto.getSenha()!=null) {

            boolean isSenhaValida = passwordEncoder.matches(dto.getSenha(), cliente.getSenha());

            System.out.println(dto.getSenha());
            System.out.println(cliente.getSenha());

            if (!isSenhaValida && dto.getSenha() != null) {
                throw new SenhaInvalidaException();
            }

            if (dto.getConfirmarSenha() != null) {
                String senhaCriptografada = passwordEncoder.encode(dto.getConfirmarSenha());
                Cliente clienteNovo = new Cliente(id, dto.getNome(), dto.getDataNascimento(), dto.getEmail(), dto.getCelular(), senhaCriptografada);
                this.clienteService.atualizarDadosCliente(id, clienteNovo);

                return new CadastrarClienteResponseDto("Dados atualizados com sucesso!");
            }
        }

        Cliente clienteNovo = new Cliente(id, dto.getNome(), dto.getDataNascimento(), dto.getEmail(), dto.getCelular(), cliente.getSenha());
        this.clienteService.atualizarDadosCliente(id, clienteNovo);

        return new CadastrarClienteResponseDto("Dados atualizados com sucesso!");

    }
}
