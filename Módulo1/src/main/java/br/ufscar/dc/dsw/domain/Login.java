package br.ufscar.dc.dsw.domain;

public class Login {
    private Agencia agencia;
    private Cliente cliente;
    private String tipo;

    public Login(Agencia agencia, Cliente cliente, String tipo) {
        this.agencia = agencia;
        this.cliente = cliente;
        this.tipo = tipo;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
