/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.DAO;

import br.com.academiaonline.model.Person;
import br.com.academiaonline.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class PersonDAOImpl {

    private Connection conn;

    public PersonDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");

        } catch (Exception ex) {
            throw new Exception("Problemas ao se conectar com o Banco de Dados! Erro: " + ex.getMessage());
        }
    }

    public Integer save(Person person) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        Integer idPerson = null;

        String sql = "INSERT INTO person"
                + "(name_person, cpf_person, birthday_date_person, email_person, password_person, type_person)"
                + " VALUES (?, ?, ?, ?, md5(?), ?)"
                + "RETURNING id_person;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getCpf());
            stmt.setDate(3, new java.sql.Date(person.getBirthdayDate().getTime()));
            stmt.setString(4, person.getEmail());
            stmt.setString(5, person.getPassword());
            stmt.setString(6, person.getType());
            rs = stmt.executeQuery();

            if (rs.next()) {
                idPerson = rs.getInt("id_person");
            }

        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar Pessoa! Erro: " + ex.getMessage());

        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão com o BD! Erro: " + ex.getMessage());
            }
        }

        return idPerson;
    }
}
