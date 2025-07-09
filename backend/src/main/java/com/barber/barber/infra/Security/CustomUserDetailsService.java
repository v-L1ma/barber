package com.barber.barber.infra.Security;

import com.barber.barber.application.services.ClienteService.ClienteService;
import com.barber.barber.domain.entities.Cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteService clienteService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       try {
           Cliente cliente = this.clienteService.buscarClientePorEmail(username);
           return new org.springframework.security.core.userdetails.User(cliente.getEmail(), cliente.getSenha(), new ArrayList<>());
       }catch (UsernameNotFoundException e){
           throw new UsernameNotFoundException("Cliente não encontrado");
       }
    }
}
