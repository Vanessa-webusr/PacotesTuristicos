package br.ufscar.dc.dsw.domain;

import java.util.ArrayList;

public class Pacote {

    private Long id;
    private String cnpj;
    private String cidade;
    private String estado;
    private String pais;
    private String partida;
    private String duracao;
    private Float valor;
    private ArrayList<String> imagem;
    private String descricao;

    public Pacote(Long id) {
        this.id = id;
    }

    public Pacote(String cnpj, String cidade, String estado, String pais, String partida,
    String duracao, Float valor, ArrayList<String> imagem, String descricao) {
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.partida = partida;
        this.duracao = duracao;
        this.valor = valor;
        this.imagem = imagem;
        this.descricao = descricao;
    }

    public Pacote(Long id, String cnpj, String cidade, String estado, String pais, String partida,
    String duracao, Float valor, ArrayList<String> imagem, String descricao) {
        this(cnpj, cidade, estado, pais, partida, duracao, valor, imagem, descricao);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public ArrayList<String> getImagem() {
        return imagem;
    }

    public void setImagem(ArrayList<String> imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

