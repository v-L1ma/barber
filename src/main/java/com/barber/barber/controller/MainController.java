package com.barber.barber.controller;

import com.barber.barber.model.Cliente;
import com.barber.barber.model.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    ApplicationContext ctx;

    @GetMapping()
    public String index(){
        return "index";
    }

    @GetMapping("/sucesso")
    public String sucesso(){
        return "sucesso";
    }

    @GetMapping("/formulario")
    public String form(Model model){
        model.addAttribute("cliente", new Cliente());
        return "formulario";
    }

    @PostMapping("/cadastro")
    public String cadastro(Model model
    , @ModelAttribute Cliente cli){
        ClienteService cs = ctx.getBean(ClienteService.class);
        cs.inserirCliente(cli);
        return "redirect:sucesso";
    }
}
