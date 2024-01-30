package br.edu.ifc.estoque.produtos.entity;

import java.util.ArrayList;

public class Venda {
    int id;
    Cliente cliente;
    ArrayList<Produto> produtos;
    ArrayList<Integer> quantidade;
    double vlrTotalPago;
    double vlrTotalVend;
    double vlrTotalGastos;
    double vlrTotalLucro;

    Venda(int id, Cliente cliente, ArrayList<Produto> produtos, ArrayList<Integer> quantidade){
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
        this.quantidade = quantidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public double getVlrTotal_gastos() {
        return vlrTotalGastos;
    }
    
    public double getVlrTotal_lucro() {
        return vlrTotalLucro;
    }
    
    public double getVlrTotal_pago() {
        return vlrTotalPago;
    }
    
    public double getVlrTotal_vend() {
        return vlrTotalVend;
    }

    public void gerarVlrTotalPago(){
        for (int i = 0; i < this.produtos.size(); i++){
            this.vlrTotalPago += this.produtos.get(i).getVlrPago()*this.quantidade.get(i);
        }
    }
}
