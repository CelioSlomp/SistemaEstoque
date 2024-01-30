package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/compras")
public class CompraController {
    @GetMapping("/listaCompras")
    public String lista_compras(){
        return "lista_compras";
    }
}
