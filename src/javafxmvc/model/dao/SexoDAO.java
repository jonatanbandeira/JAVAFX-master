package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxmvc.model.domain.Sexo;

public class SexoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Sexo sexo) {
        String sql = "INSERT INTO sexo(cdSexo, nomeSexo) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sexo.getCdSexo());
            stmt.setString(2, sexo.getNomeSexo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SexoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Sexo sexo) {
        String sql = "UPDATE sexo SET nomeSexo=? WHERE cdSexo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sexo.getCdSexo());
            stmt.setString(2, sexo.getNomeSexo());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SexoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Sexo sexo) {
        String sql = "DELETE FROM sexo WHERE cdSexo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sexo.getCdSexo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SexoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Sexo> listar() {
        String sql = "SELECT * FROM sexo";
        List<Sexo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Sexo sexo = new Sexo();
                sexo.setCdSexo(resultado.getInt("cdSexo"));
                sexo.setNomeSexo(resultado.getString("nomeSexo"));
                retorno.add(sexo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SexoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Sexo buscar(Sexo sexo) {
        String sql = "SELECT * FROM sexo WHERE cdSexo=?";
        Sexo retorno = new Sexo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sexo.getCdSexo());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                
               
                
                sexo.setCdSexo(resultado.getInt("cdSexo"));
                sexo.setNomeSexo(resultado.getString("nomeSexo"));
                
                retorno = sexo;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SexoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
