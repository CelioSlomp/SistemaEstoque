package br.edu.ifc.estoque.produtos.entity;

public class Produto {
    int id;
    String nome;
    String marca;
    int tipo;
    String descricao;
    int quantidade;
    String unidade;
    double vlrPago;

    Produto(int id, String nome, String marca,int tipo, String descricao, int quantidade, String unidade){
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.tipo = tipo;
        this.descricao = descricao;
        this.unidade = unidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public int getTipo() {
        return tipo;
    }
    
    public String getUnidade() {
        return unidade;
    }
    
    public double getVlrPago() {
        return vlrPago;
    }

    public String getMarca() {
        return marca;
    }
}
