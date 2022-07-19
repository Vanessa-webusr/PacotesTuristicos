package br.ufscar.dc.dsw.domain;

public class Cliente {

    private Long id;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private String sexo;
    private Long nascimento;

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(String email, String senha, String cpf, String telefone,
            String sexo, Long nascimento) {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.nascimento = nascimento;
    }

    public Cliente(Long id, String email, String senha, String cpf, String telefone,
    String sexo, Long nascimento) {
        this(email, senha, cpf, telefone, sexo, nascimento);
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

    public String getCpf(String cpf) {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Long getNascimento() {
        return nascimento;
    }

    public void setNascimento(Long nascimento) {
        this.nascimento = nascimento;
    }
}
