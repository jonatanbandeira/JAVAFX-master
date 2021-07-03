package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxmvc.model.domain.Cidade;
import javafxmvc.model.domain.Pet;
import javafxmvc.model.domain.Porte;
import javafxmvc.model.domain.Raca;
import javafxmvc.model.domain.Sexo;

public class PetDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Pet pet) {
        String sql = "INSERT INTO pets(nomePet, CdRaca, CdPorte, CdSexo, nomeDono, telefone, email, CdCidade) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pet.getNomePet());
            stmt.setInt(2, pet.getRaca().getCdRaca());
            stmt.setInt(3, pet.getPorte().getCdPorte());
            stmt.setInt(4, pet.getSexo().getCdSexo());
            stmt.setString(5, pet.getNomeDono());
            stmt.setString(6, pet.getTelefone());
            stmt.setString(7, pet.getEmail());
            stmt.setInt(8, pet.getCidade().getCdCidade());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PetDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Pet pet) {
        String sql = "UPDATE pets SET nomePet=?, CdRaca=?, CdPorte=?, CdSexo=?, nomeDono=?, telefone=?, email=?, CdCidade=? WHERE cdPet=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pet.getNomePet());
            stmt.setInt(2, pet.getRaca().getCdRaca());
            stmt.setInt(3, pet.getPorte().getCdPorte());
            stmt.setInt(4, pet.getSexo().getCdSexo());
            stmt.setString(5, pet.getNomeDono());
            stmt.setString(6, pet.getTelefone());
            stmt.setString(7, pet.getEmail());
            stmt.setInt(8, pet.getCidade().getCdCidade());
            stmt.setInt(9, pet.getCdPet());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PetDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Pet pet) {
        String sql = "DELETE FROM pets WHERE cdPet=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pet.getCdPet());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PetDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Pet> listar() {
        String sql = "SELECT * FROM Pets";
        List<Pet> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                
                Pet pet = new Pet();
                Raca raca = new Raca();
                Porte porte = new Porte();
                Sexo sexo = new Sexo();
                Cidade cidade = new Cidade();  
                
                raca.setCdRaca(resultado.getInt("cdRaca"));
                porte.setCdPorte(resultado.getInt("cdPorte"));
                sexo.setCdSexo(resultado.getInt("cdSexo"));
                cidade.setCdCidade(resultado.getInt("cdCidade"));
                
                pet.setCdPet(resultado.getInt("cdPet"));
                pet.setNomePet(resultado.getString("nomePet"));
                pet.setNomeDono(resultado.getString("nomeDono"));
                pet.setTelefone(resultado.getString("telefone"));
                pet.setEmail(resultado.getString("email"));
                
                RacaDAO racaDAO = new RacaDAO();
                racaDAO.setConnection(connection);
                raca = racaDAO.buscar(raca);
                
                PorteDAO porteDAO = new PorteDAO();
                porteDAO.setConnection(connection);
                porte = porteDAO.buscar(porte);
                
                SexoDAO sexoDAO = new SexoDAO();
                sexoDAO.setConnection(connection);
                sexo = sexoDAO.buscar(sexo);
                
                CidadeDAO cidadeDAO = new CidadeDAO();
                cidadeDAO.setConnection(connection);
                cidade = cidadeDAO.buscar(cidade);
                
                pet.setRaca(raca);
                pet.setPorte(porte);
                pet.setSexo(sexo);
                pet.setCidade(cidade);
                
                retorno.add(pet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Pet buscar(Pet pet) {
        String sql = "SELECT * FROM pets WHERE cdPet=?";
        Pet retorno = new Pet();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pet.getCdPet());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                
                Raca raca = new Raca();
                Porte porte = new Porte();
                Sexo sexo = new Sexo();
                Cidade cidade = new Cidade(); 
                
                raca.setCdRaca(resultado.getInt("cdRaca"));
                porte.setCdPorte(resultado.getInt("cdPorte"));
                sexo.setCdSexo(resultado.getInt("cdSexo"));
                cidade.setCdCidade(resultado.getInt("cdCidade"));
                
                pet.setCdPet(resultado.getInt("cdPet"));
                pet.setNomePet(resultado.getString("nomePet"));
                pet.setNomeDono(resultado.getString("nomeDono"));
                pet.setTelefone(resultado.getString("telefone"));
                pet.setEmail(resultado.getString("email"));
                
                pet.setRaca(raca);
                pet.setPorte(porte);
                pet.setSexo(sexo);
                pet.setCidade(cidade);
                
                retorno = pet;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
   public Map<String, Integer> listarQuantidadeRacaCadastradas() {
        String sql = "select nomeRaca as RACA, count(*) as QUANTIDADE from raca r, pets p where r.cdRaca = p.cdRaca group by RACA";
        Map<String, Integer> retorno = new HashMap();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                ArrayList linha = new ArrayList();
                if (!retorno.containsKey(resultado.getInt("QUANTIDADE")))
                {
                    //linha.add(resultado.getString("RACA"));;
                    //linha.add(resultado.getInt("QUANTIDADE"));
                    retorno.put(resultado.getString("RACA"), resultado.getInt("QUANTIDADE"));
                }else{
                    /*ArrayList linhaNova = retorno.get(resultado.getString("RACA"));
                    linhaNova.add(resultado.getString("RACA"));
                    linhaNova.add(resultado.getInt("QUANTIDADE"));*/
                }
            }
            return retorno;
        } catch (SQLException ex) {
            System.out.println("Aqui");
            Logger.getLogger(PetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
