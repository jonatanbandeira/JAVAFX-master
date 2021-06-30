package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxmvc.model.domain.Porte;

public class PorteDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Porte porte) {
        String sql = "INSERT INTO porte(cdPorte, nomePorte) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, porte.getCdPorte());
            stmt.setString(2, porte.getNomePorte());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PorteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Porte porte) {
        String sql = "UPDATE porte SET nomePorte=? WHERE cdPorte=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, porte.getCdPorte());
            stmt.setString(2, porte.getNomePorte());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PorteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Porte porte) {
        String sql = "DELETE FROM porte WHERE cdPorte=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, porte.getCdPorte());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PorteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Porte> listar() {
        String sql = "SELECT * FROM porte";
        List<Porte> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Porte porte = new Porte();
                porte.setCdPorte(resultado.getInt("cdPorte"));
                porte.setNomePorte(resultado.getString("nomePorte"));
                retorno.add(porte);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PorteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Porte buscar(Porte porte) {
        String sql = "SELECT * FROM porte WHERE cdPorte=?";
        Porte retorno = new Porte();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, porte.getCdPorte());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                
                porte.setCdPorte(resultado.getInt("cdPorte"));
                porte.setNomePorte(resultado.getString("nomePorte"));
                
                retorno = porte;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PorteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
