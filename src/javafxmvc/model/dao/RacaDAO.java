package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxmvc.model.domain.Raca;

public class RacaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Raca raca) {
        String sql = "INSERT INTO raca(cdRaca, nomeRaca) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, raca.getCdRaca());
            stmt.setString(2, raca.getNomeRaca());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RacaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Raca raca) {
        String sql = "UPDATE raca SET nomeRaca=? WHERE cdRaca=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, raca.getCdRaca());
            stmt.setString(2, raca.getNomeRaca());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RacaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Raca raca) {
        String sql = "DELETE FROM raca WHERE cdRaca=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, raca.getCdRaca());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RacaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Raca> listar() {
        String sql = "SELECT * FROM raca";
        List<Raca> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Raca raca = new Raca();
                raca.setCdRaca(resultado.getInt("cdRaca"));
                raca.setNomeRaca(resultado.getString("nomeRaca"));
                retorno.add(raca);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RacaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Raca buscar(Raca raca) {
        String sql = "SELECT * FROM raca WHERE cdRaca=?";
        Raca retorno = new Raca();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, raca.getCdRaca());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                
                raca.setCdRaca(resultado.getInt("cdRaca"));
                raca.setNomeRaca(resultado.getString("nomeRaca"));
                
                retorno = raca;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RacaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
