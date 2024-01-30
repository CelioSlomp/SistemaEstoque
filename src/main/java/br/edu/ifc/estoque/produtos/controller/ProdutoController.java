package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @GetMapping("/listaProdutos")
    public String listaProdutos(){
        return "lista_produtos";
    }

    @GetMapping("/cadastrarProduto")
    public String cadastrarProduto(){
        return "cadastrar_produto";
    }

    
}
