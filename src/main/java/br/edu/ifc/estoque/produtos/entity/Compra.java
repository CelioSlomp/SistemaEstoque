package br.edu.ifc.estoque.produtos.entity;

import java.util.ArrayList;

public class Compra {
    int id;
    ArrayList<Produto> produtos;
    ArrayList<Double> vlrPago;

    Compra(int id, ArrayList<Produto> produtos, ArrayList<Double> vlrPago){
        this.id = id;
        this.produtos = produtos;
        this.vlrPago = vlrPago;
    }

    public int getId() {
        return id;
    }
    
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }
    
    public ArrayList<Double> getVlrPago() {
        return vlrPago;
    }


}
