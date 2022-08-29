package br.ufscar.dc.dsw.domain;

import java.util.List;
import br.ufscar.dc.dsw.domain.Imagem;

public class Pacote {

    private Long id;
    private String cnpj;
    private Long agencia_id;
    private String cidade;
    private String estado;
    private String pais;
    private String partida;
    private String duracao;
    private Float valor;
    private Imagem[] imagem;
    private String descricao;

    public Pacote(Long id) {
        this.id = id;
    }

    public Pacote(String cnpj, Long agencia_id, String cidade, String estado, String pais, String partida,
    String duracao, Float valor, Imagem[] imagem, String descricao) {
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.partida = partida;
        this.duracao = duracao;
        this.valor = valor;
        this.imagem = imagem;
        this.descricao = descricao;
        this.agencia_id = agencia_id;
    }

    public Pacote(Long id, String cnpj, Long agencia_id, String cidade, String estado, String pais, String partida,
    String duracao, Float valor, Imagem[] imagem, String descricao) {
        this(cnpj,agencia_id, cidade, estado, pais, partida, duracao, valor, imagem, descricao);
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

    public Imagem[] getImagem() {
        return imagem;
    }

    public void setImagem(Imagem[] imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getAgenciaId(){
        return agencia_id;
    }

    public void setAgenciaId(Long agencia_id){
        this.agencia_id = agencia_id;
    }
}

