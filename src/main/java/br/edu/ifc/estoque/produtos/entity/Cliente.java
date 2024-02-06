package br.edu.ifc.estoque.produtos.entity;

public class Cliente {
    String id;
    String nome;
    int bomPag;

    Cliente(String id, String nome, int bomPag){
        this.id = id;
        this.nome = nome;
        this.bomPag = bomPag;
    }

    public String getNome() {
        return nome;
    }

    public int getBomPag() {
        return bomPag;
    }
    
}
