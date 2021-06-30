package javafxmvc.model.domain;

import java.io.Serializable;

public class Porte implements Serializable  {
    private int cdPorte;
    private String nomePorte;
    
    public Porte() {
    }
    
    public Porte(int cdPorte, String nomePorte) {
        this.cdPorte = cdPorte;
        this.nomePorte = nomePorte;
    }
    
    public int getCdPorte() {
        return cdPorte;
    }
    
    public void setCdPorte(int cdPorte) {
        this.cdPorte = cdPorte;
    }
    
    public String getNomePorte() {
        return nomePorte;
    }

    public void setNomePorte(String nomePorte) {
        this.nomePorte = nomePorte;
    }
    
    @Override
    public String toString() {
        return this.nomePorte;
    }
}
