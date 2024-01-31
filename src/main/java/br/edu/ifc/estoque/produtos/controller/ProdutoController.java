package br.edu.ifc.estoque.produtos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ifc.estoque.produtos.entity.Produto;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProdutoController {
    @GetMapping("/listaProdutos")
    public String listaProdutos(){
        return "lista_produtos";
    }

    @PostMapping("/cadastrarProduto")
    public void cadastrarProduto(@RequestBody Produto produto) {
        // Imprimir os dados recebidos na tela
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Marca: " + produto.getMarca());
        System.out.println("Tipo: " + produto.getTipo());
        System.out.println("Descrição: " + produto.getDescricao());
        System.out.println("Quantidade: " + produto.getQuantidade());
        System.out.println("Unidade: " + produto.getUnidade());
    }

    
}
