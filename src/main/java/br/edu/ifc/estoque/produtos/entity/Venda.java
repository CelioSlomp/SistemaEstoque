package br.edu.ifc.estoque.produtos.entity;

public class Venda {
    String prodNome;
    String clieNome;
    int idProduto;
    int idCliente;
    int quantidade;
    double valor;

    public Venda() {
    }

    public Venda(int idProduto, int idCliente, int quantidade, double valor) {
        this.idProduto = idProduto;
        this.idCliente = idCliente;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Venda(String prodNome, String clieNome, int quantidade, double valor) {
        this.prodNome = prodNome;
        this.clieNome = clieNome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public String getClieNome() {
        return clieNome;
    }

    public String getProdNome() {
        return prodNome;
    }

}
