/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.model.domain;

import java.io.Serializable;

/**
 *
 * @author victor
 */
public class Raca implements Serializable {
    private int cdRaca;
    private String nomeRaca;
    
    public Raca() {
    }
    
    public Raca(int cdRaca, String nomeRaca){
        this.cdRaca = cdRaca;
        this.nomeRaca = nomeRaca;
    }
    
    public int getCdRaca() {
        return cdRaca;
    }
    
    public void setCdRaca(int cdRaca) {
        this.cdRaca = cdRaca;
    }
    
    public String getNomeRaca() {
        return nomeRaca;
    }
    
    public void setNomeRaca(String nomeRaca) {
        this.nomeRaca = nomeRaca;
    }
    
    @Override
    public String toString() {
        return this.nomeRaca;
    }

}
