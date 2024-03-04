package br.edu.ifc.estoque.produtos.entity;

public class Compra {
    int id;
    int quantidade;
    double vlrPago;

    Compra(int id, int quantidade, double vlrPago) {
        this.id = id;
        this.quantidade = quantidade;
        this.vlrPago = vlrPago;
    }

    public int getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getVlrPago() {
        return vlrPago;
    }

}
