package br.edu.ifc.estoque.produtos.entity;

import java.util.HashMap;

public class Produto {
    int id;
    String nome;
    String marca;
    int tipo;
    String descricao;
    int quantidade;
    String unidade;
    double vlrPago;
    HashMap<Double, Integer> valoresPagos; 
    
    public Produto(){}

    public Produto(int id, String nome, String marca,int tipo, String descricao, int quantidade, String unidade){
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setValoresPagos(HashMap<Double, Integer> valoresPagos) {
        this.valoresPagos = valoresPagos;
    }

    public void setVlrPago(double vlrPago) {
        this.vlrPago = vlrPago;
    }
}
