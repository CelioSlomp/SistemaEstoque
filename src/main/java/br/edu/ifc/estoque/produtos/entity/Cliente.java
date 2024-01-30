package br.edu.ifc.estoque.produtos.entity;

import java.util.ArrayList;

public class Cliente {
    String id;
    String nome;
    int bomPag;
    ArrayList<Venda> vendas;

    Cliente(String id, String nome, int bomPag){
        this.id = id;
        this.nome = nome;
        this.bomPag = bomPag;
    }

    void adicionarVenda(Venda v){
        this.vendas.add(v);
    }

    
}
