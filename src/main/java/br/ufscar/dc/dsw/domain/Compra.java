package br.ufscar.dc.dsw.domain;

public class Compra {
    
    private Long id;
    private Long pacoteId;
    private Long clienteId;
    private Double valor;

    public Compra(Long id) {
        this.id = id;
    }

    public Compra(Long pacoteId, Long clienteId, Double valor) {
        this.pacoteId  = pacoteId;
        this.clienteId = clienteId;
        this.valor = valor;
    }

    public Compra(Long id, Long pacoteId, Long clienteId, Double valor){
        this(pacoteId, clienteId, valor);
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getPacote(){
        return pacoteId;
    }

    public void setPacote(Long pacoteId){
        this.pacoteId = pacoteId;
    }

    public Long getCliente(){
        return clienteId;
    }

    public void setCliente(Long clienteId){
        this.clienteId = clienteId;
    }

    public Double getValor(){
        return valor;
    }

    public void setValor(Double valor){
        this.valor = valor;
    }

}
