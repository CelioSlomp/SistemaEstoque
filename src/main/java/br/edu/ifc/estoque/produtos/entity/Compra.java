package br.edu.ifc.estoque.produtos.entity;

public class Compra {
    String nome;
    int idProduto;
    int quantidade;
    double vlrPago;

    public Compra(String nome, int idProduto, int quantidade, double vlrPago) {
        this.nome = nome;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.vlrPago = vlrPago;
    }

    public String getNome() {
        return nome;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getVlrPago() {
        return vlrPago;
    }

}
