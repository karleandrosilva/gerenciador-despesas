package com.ufal.gerenciador_despesas.model;

import java.math.BigDecimal;

public class Despesa {

    private Long id; // id para cada despesa
    private String descricao; // descrição para cada despesa
    private BigDecimal valor; // BigDecimal pois tem precisão decimal exata quando se usa dinheiro
    private String categoria; // para agrupar os gastos (ex: alimentação, lazer...)
    private String mesReferencia; // qual mês a despesa pertence (ex: "06/2026")

    // construtor padrão
    public Despesa() {
    }

    // construtor completo
    public Despesa(Long id, String descricao, BigDecimal valor, String categoria, String mesReferencia) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.mesReferencia = mesReferencia;
    }

    // getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }
}
