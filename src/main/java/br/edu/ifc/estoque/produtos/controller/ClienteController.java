package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @GetMapping("/lista_clientes")
    public String listaClientes(){
        return "lista_clientes";
    }

    @GetMapping("/cadastrar_cliente")
    public String cadastrarCliente(){
        return "lista_clientes";
    }
}
