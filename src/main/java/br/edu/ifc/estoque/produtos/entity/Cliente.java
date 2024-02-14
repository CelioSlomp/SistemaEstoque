package br.edu.ifc.estoque.produtos.entity;

public class Cliente {
    int id;
    String nome;
    int bomPag;

    public Cliente(int id, String nome, int bomPag){
        this.id = id;
        this.nome = nome;
        this.bomPag = bomPag;
    }

    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public int getBomPag() {
        return bomPag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setBomPag(int bomPag) {
        this.bomPag = bomPag;
    }
    
}
