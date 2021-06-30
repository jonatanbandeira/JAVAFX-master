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
public class Cidade implements Serializable {
    private int cdCidade;
    private String nomeCidade;
        
    public Cidade() {
    }
    
    public Cidade(int cdCidade, String nomeCidade){
        this.cdCidade = cdCidade;
        this.nomeCidade = nomeCidade;
    }
    
    public int getCdCidade() {
        return cdCidade;
    }
    
    public void setCdCidade(int cdCidade) {
        this.cdCidade = cdCidade;
    }
    
    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
    
    @Override
    public String toString() {
        return this.nomeCidade;
    }
}