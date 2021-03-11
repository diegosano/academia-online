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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

        String sql = "INSERT INTO user_account(registration_date_user, profile_picture_user, id_person) VALUES (?, ?, ?);";

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(user.getRegistratioDate().getTime()));
            stmt.setBinaryStream(2, user.getProfilePicture(), user.getFileItem());
            stmt.setInt(3, new PersonDAOImpl().save(user));
            stmt.execute();
            return true;

        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Cliente! Erro: " + ex.getMessage());
            return false;

        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão com o BD! Erro: " + ex.getMessage());
            }
        }
    }

    @Override
    public List<Object> findAll() {
        List<Object> result = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT p.*, u.* "
                + "FROM person p, user_account u "
                + "WHERE p.id_person = u.id_person "
                + "ORDER BY p.name_person";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id_person"));
                user.setName(rs.getString("name_person"));
                user.setCpf(rs.getString("cpf_person"));
                user.setBirthdayDate(rs.getDate("birthday_date_person"));
                user.setEmail(rs.getString("email_person"));
                user.setPassword(rs.getString("password_person"));
                user.setType(rs.getString("type_person"));
                user.setRegistratioDate(rs.getDate("registration_date_user"));
                result.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar User! Erro: " + ex.getMessage());

        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão com o BD! Erro: " + ex.getMessage());
            }
        }

        return result;
    }

    @Override
    public void deleteById(Integer idObject) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM user_account WHERE id_person = ?; "
                + "COMMIT WORK; "
                + "DELETE FROM person WHERE id_person = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.setInt(2, idObject);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir User! Erro: " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão!Erro: " + ex.getMessage());
            }
        }
    }

    @Override
    public Object findById(Integer idObject
    ) {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT p.id_person, "
                + "p.name_person, "
                + "p.cpf_person, "
                + "p.birthday_date_person, "
                + "p.email_person, "
                + "ua.registration_date_user "
                + "FROM person p, user_account ua "
                + "WHERE p.id_person AND ua.id_person = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id_person"));
                user.setName(rs.getString("name_person"));
                user.setCpf(rs.getString("cpf_person"));
                user.setBirthdayDate(rs.getDate("birthday_date_person"));
                user.setEmail(rs.getString("email_person"));
                user.setRegistratioDate(rs.getDate("registration_date_user"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar User! Erro: " + ex.getMessage());

        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão com o BD! Erro: " + ex.getMessage());
            }
        }

        return user;
    }

    @Override
    public Boolean update(Object object
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User getProfilePicture(int idUser) {

        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT profile_picture_user FROM user_account WHERE id_user=?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUser);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setProfilePicture(rs.getBinaryStream("profile_picture_user"));
            }

        } catch (SQLException ex) {
            System.out.println("Problemas ao listar foto do User! Erro: " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão!Erro: " + ex.getMessage());
            }
        }
        return user;
    }

}
