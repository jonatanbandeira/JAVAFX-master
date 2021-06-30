package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxmvc.model.domain.Cidade;

public class CidadeDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Cidade cidade) {
        String sql = "INSERT INTO cidade(cdCidade, nomeCidade) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cidade.getCdCidade());
            stmt.setString(2, cidade.getNomeCidade());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Cidade cidade) {
        String sql = "UPDATE cidade SET nomeCidade=? WHERE cdCidade=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cidade.getCdCidade());
            stmt.setString(2, cidade.getNomeCidade());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Cidade cidade) {
        String sql = "DELETE FROM cidade WHERE cdCidade=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cidade.getCdCidade());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Cidade> listar() {
        String sql = "SELECT * FROM cidade";
        List<Cidade> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cidade cidade = new Cidade();
                cidade.setCdCidade(resultado.getInt("cdCidade"));
                cidade.setNomeCidade(resultado.getString("nomeCidade"));
                retorno.add(cidade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Cidade buscar(Cidade cidade) {
        String sql = "SELECT * FROM cidade WHERE cdCidade=?";
        Cidade retorno = new Cidade();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cidade.getCdCidade());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                
                cidade.setCdCidade(resultado.getInt("cdCidade"));
                cidade.setNomeCidade(resultado.getString("nomeCidade"));
                
                retorno = cidade;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
