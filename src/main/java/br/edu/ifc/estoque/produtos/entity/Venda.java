package br.edu.ifc.estoque.produtos.entity;

public class Venda {
    int idProduto;
    int idCliente;
    int quantidade;
    double valor;

    public Venda(int idProduto, int idCliente, int quantidade, double valor) {
        this.idProduto = idProduto;
        this.idCliente = idCliente;
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

}
