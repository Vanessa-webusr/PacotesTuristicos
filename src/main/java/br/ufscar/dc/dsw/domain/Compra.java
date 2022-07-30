package br.ufscar.dc.dsw.domain;

public class Compra {
    
    private Long id;
    private Pacote pacote;
    private Long clienteId;
    private Float valor;
    private int ativo;

    public Compra(Long id) {
        this.id = id;
    }

    public Compra(Pacote pacote, Long clienteId, Float valor, int ativo) {
        this.pacote  = pacote;
        this.clienteId = clienteId;
        this.valor = valor;
        this.ativo = ativo;
    }

    public Compra(Long id, Pacote pacote, Long clienteId, Float valor, int ativo){
        this(pacote, clienteId, valor, ativo);
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Pacote getPacote(){
        return pacote;
    }

    public void setPacote(Pacote pacote){
        this.pacote = pacote;
    }

    public Long getCliente(){
        return clienteId;
    }

    public void setCliente(Long clienteId){
        this.clienteId = clienteId;
    }

    public Float getValor(){
        return valor;
    }

    public void setValor(Float valor){
        this.valor = valor;
    }

    public int getAtivo(){
        return ativo;
    }

    public void setAtivo(int ativo){
        this.ativo = ativo;
    }

}
