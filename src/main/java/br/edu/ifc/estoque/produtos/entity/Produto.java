package br.edu.ifc.estoque.produtos.entity;

public class Produto {
    int id;
    String nome;
    int tipo;
    String descricao;
    int quantidade;
    String unidade;
    double vlrPago;

    Produto(int id, String nome, int tipo, String descricao, int quantidade, String unidade, double vlrPago){
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.vlrPago = vlrPago;
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

    
}
