package br.edu.ifc.estoque.produtos.entity;

public class Compra {
    int idProduto;
    int quantidade;
    double vlrPago;

    public Compra(int idProduto, int quantidade, double vlrPago) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.vlrPago = vlrPago;
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
