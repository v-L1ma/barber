package com.barber.barber.application.services.ClienteService;

import com.barber.barber.domain.entities.Cliente.Cliente;
import com.barber.barber.domain.repositories.ClienteRepository.IClienteRepository;
import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    IClienteRepository clienteRepository;

    @Override
    public void cadastrarCliente(CadastrarClienteDto dto) {
        clienteRepository.cadastrarCliente(dto);
    }

    @Override
    public Cliente buscarClientePorEmail(String email) {
        if (clienteRepository.listarClientePorEmail(email)==null){
            return null;
        }

        return Cliente.converter(clienteRepository.listarClientePorEmail(email));
    }

    @Override
    public Cliente buscarClientePorId(int id) {
        if (clienteRepository.listarClientePorId(id)==null){
            return null;
        }

        return Cliente.converter(clienteRepository.listarClientePorId(id));
    }

    @Override
    public void atualizarDadosCliente(int id, Cliente novo) {
        clienteRepository.atualizarDadosCliente(id,novo);
    }

    @Override
    public void deletarCliente(int id) {
        clienteRepository.deletarCliente(id);
    }
}
