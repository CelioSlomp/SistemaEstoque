package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class VendaController {
    @GetMapping("/listaVendas")
    public String lista_vendas() {
        return "lista_vendas";
    }
}
