package br.ufscar.dc.dsw.domain;

public class Agencia {

    private Long id;
    private String email;
    private String senha;
    private String cnpj;
    private String nome;
    private String descricao;

    public Agencia(Long id) {
        this.id = id;
    }

    public Agencia(String email, String senha, String cnpj, String nome,
            String descricao) {
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Agencia(Long id, String email, String senha, String cnpj, String nome,
    String descricao) {
        this(email, senha, cnpj, nome, descricao);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
