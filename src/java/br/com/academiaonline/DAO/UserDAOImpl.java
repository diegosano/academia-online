/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.DAO;

import br.com.academiaonline.model.User;
import br.com.academiaonline.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego
 */
public class UserDAOImpl implements GenericDAO {

    private Connection conn;

    public UserDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");

        } catch (Exception ex) {
            throw new Exception("Problemas ao se conectar com o Banco de Dados! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean save(Object object) {
        User user = (User) object;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO user_account(registratio_date_user, profile_picture_user, id_person) VALUES (?, ?, ?);";

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(user.getRegistratioDate().getTime()));
            stmt.setBinaryStream(2, user.getProfilePicture(), user.getFileItem());
            stmt.setInt(3, new PersonDAOImpl().save(user));
            stmt.execute();
            return true;

        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Cliente! Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;

        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão com o BD! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Integer idObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object findById(Integer idObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean update(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
