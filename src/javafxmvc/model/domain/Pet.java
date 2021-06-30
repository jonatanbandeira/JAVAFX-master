package javafxmvc.model.domain;

import java.io.Serializable;

public class Pet implements Serializable {

    private int cdPet;
    private String nomePet;
    private Raca raca;
    private Porte porte;
    private Sexo sexo;
    private String nomeDono;
    private String telefone;
    private String email;
    private Cidade cidade;

    public Raca getCdRaca() {
        return cdRaca;
    }

    public void setCdRaca(Raca cdRaca) {
        this.cdRaca = cdRaca;
    }

    public Porte getCdPorte() {
        return cdPorte;
    }

    public void setCdPorte(Porte cdPorte) {
        this.cdPorte = cdPorte;
    }

    public Sexo getCdSexo() {
        return cdSexo;
    }

    public void setCdSexo(Sexo cdSexo) {
        this.cdSexo = cdSexo;
    }

    public Cidade getCdCidade() {
        return cdCidade;
    }

    public void setCdCidade(Cidade cdCidade) {
        this.cdCidade = cdCidade;
    }
    
    private Raca cdRaca;
    private Porte cdPorte;
    private Sexo cdSexo;
    private Cidade cdCidade;
    

    public Pet(){
    }
    
    public Pet(int cdPet, Cidade cdCidade, Porte cdPorte, Raca cdRaca, Sexo cdSexo, String nomePet, String nomeDono, String telefone, String email) {
        this.cdPet = cdPet;
        this.nomePet = nomePet;
        this.nomeDono = nomeDono;
        this.telefone = telefone;
        this.email = email;
        this.cdCidade = cdCidade;
        this.cdPorte = cdPorte;
        this.cdRaca = cdRaca;
        this.cdSexo = cdSexo;
        
        
    }

    public int getCdPet() {
        return cdPet;
    }

    public void setCdPet(int cdPet) {
        this.cdPet = cdPet;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }
    
    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }
    
    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    
    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    @Override
    public String toString() {
        return this.nomePet;
    }
    
}
