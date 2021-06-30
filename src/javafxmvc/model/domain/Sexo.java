package javafxmvc.model.domain;

import java.io.Serializable;

public class Sexo implements Serializable  {
    private int cdSexo;
    private String nomeSexo;
    
    public Sexo() {
    }
    
    public Sexo(int cdSexo, String nomeSexo) {
        this.cdSexo = cdSexo;
        this.nomeSexo = nomeSexo;
    }
    
    public int getCdSexo() {
        return cdSexo;
    }
    
    public void setCdSexo(int cdSexo) {
        this.cdSexo = cdSexo;
    }
    
    public String getNomeSexo() {
        return nomeSexo;
    }
    
    public void setNomeSexo(String nomeSexo) {
        this.nomeSexo = nomeSexo;
    }
    
    @Override
    public String toString() {
        return this.nomeSexo;
    }
}
