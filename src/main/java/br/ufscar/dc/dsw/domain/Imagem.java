package br.ufscar.dc.dsw.domain;

import java.util.List;

public class Imagem {

    private Long id;
    private Long pacote_id;
    private String link;

    public Imagem() {
        
    }

    public Imagem(Long id) {
        this.id = id;
    }

    public Imagem(Long pacote_id, String link) {
        this.pacote_id = pacote_id;
        this.link = link;
    }

    public Imagem(Long id, Long pacote_id, String link) {
        this(pacote_id, link);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacoteId(){
        return pacote_id;
    }

    public void setPacoteId(Long pacote_id){
        this.pacote_id = pacote_id;
    }

    public String getLink(){
        return link;
    }

    public void setLink(String link){
        this.link = link;
    }
}

