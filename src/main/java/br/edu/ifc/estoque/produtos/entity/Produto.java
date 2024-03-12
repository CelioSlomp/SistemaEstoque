package br.edu.ifc.estoque.produtos.entity;

import java.util.HashMap;

public class Produto {
    int id;
    String nome;
    String marca;
    String tipo;
    String descricao;
    int quantidade;
    String unidade;
    double vlrPago;
    HashMap<Double, Integer> valoresPagos;

    public Produto(int id, String nome, String marca, String tipo, String descricao, String unidade, int quantidade,
            double vlrPago) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
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

    public String getTipo() {
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

    public void setTipo(String tipo) {
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
