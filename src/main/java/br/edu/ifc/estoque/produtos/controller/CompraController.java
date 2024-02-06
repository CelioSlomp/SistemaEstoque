package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/compras")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CompraController {
    @GetMapping("/listaCompras")
    public String lista_compras(){
        return "lista_compras";
    }

    @PostMapping("/realizarCompra")
    public void realizarCompra(){
        
    }
}
