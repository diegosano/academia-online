/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.DAO;

import br.com.academiaonline.model.Employee;
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
public class EmployeeDAOImpl implements GenericDAO {

    private Connection conn;

    public EmployeeDAOImpl() throws Exception {
        try {

            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");

        } catch (Exception ex) {
            throw new Exception("Problemas ao se conectar com o Banco de Dados! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean save(Object object) {

        Employee employee = (Employee) object;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO employee "
                + "(curriculum_employee, "
                + "address_employee, "
                + "city_employee, "
                + "state_employee, "
                + "postal_code_employee, "
                + "telephone_employee, "
                + "id_person) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, employee.getCurriculumVitae());
            stmt.setString(2, employee.getAddress());
            stmt.setString(3, employee.getCity());
            stmt.setString(4, employee.getState());
            stmt.setString(5, employee.getPostalCode());
            stmt.setString(6, employee.getTelephone());
            stmt.setInt(7, new PersonDAOImpl().save(employee));
            stmt.execute();
            return true;

        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Funcionário! Erro: " + ex.getMessage());
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

        String sql = "SELECT "
                + "p.id_person, "
                + "p.name_person, "
                + "p.cpf_person, "
                + "p.email_person, "
                + "e.curriculum_employee, "
                + "e.telephone_employee "
                + "FROM person p, employee e "
                + "WHERE p.id_person = e.id_person "
                + "ORDER BY p.name_person;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id_person"));
                employee.setName(rs.getString("name_person"));
                employee.setCpf(rs.getString("cpf_person"));
                employee.setEmail(rs.getString("email_person"));
                employee.setTelephone(rs.getString("telephone_employee"));
                employee.setCurriculumVitae(rs.getString("curriculum_employee"));
                result.add(employee);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Employee! Erro: " + ex.getMessage());

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
        String sql = "DELETE FROM employee "
                + "WHERE id_person = ?; "
                + "COMMIT WORK; "
                + "DELETE FROM person "
                + "WHERE id_person = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.setInt(2, idObject);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir Employee! Erro: " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
            }
        }
    }

    @Override
    public Object findById(Integer idObject) {
        Employee employee = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT p.id_person, "
                + "p.id_person, "
                + "p.name_person, "
                + "p.cpf_person, "
                + "p.birthday_date_person, "
                + "p.email_person, "
                + "p.password_person, "
                + "e.curriculum_employee, "
                + "e.telephone_employee, "
                + "e.address_employee, "
                + "e.city_employee, "
                + "e.state_employee, "
                + "e.postal_code_employee "
                + "FROM person p, employee e "
                + "WHERE p.id_person = e.id_person AND p.id_person = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            if (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getInt("id_person"));
                employee.setName(rs.getString("name_person"));
                employee.setCpf(rs.getString("cpf_person"));
                employee.setBirthdayDate(rs.getDate("birthday_date_person"));
                employee.setEmail(rs.getString("email_person"));
                employee.setPassword(rs.getString("password_person"));
                employee.setCurriculumVitae(rs.getString("curriculum_employee"));
                employee.setTelephone(rs.getString("telephone_employee"));
                employee.setAddress(rs.getString("address_employee"));
                employee.setCity(rs.getString("city_employee"));
                employee.setState(rs.getString("state_employee"));
                employee.setPostalCode(rs.getString("postal_code_employee"));
                employee.setPassword(rs.getString("password_person"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar Employee! Erro: " + ex.getMessage());

        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão com o BD! Erro: " + ex.getMessage());
            }
        }

        return employee;
    }

    @Override
    public Boolean update(Object object) {

        Employee employee = (Employee) object;
        PreparedStatement stmt = null;

        String sql = "UPDATE employee "
                + "SET curriculum_employee=?, "
                + "address_employee=?, "
                + "city_employee=?, "
                + "state_employee=?, "
                + "postal_code_employee=?, "
                + "telephone_employee=? "
                + "WHERE id_person = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, employee.getCurriculumVitae());
            stmt.setString(2, employee.getAddress());
            stmt.setString(3, employee.getCity());
            stmt.setString(4, employee.getState());
            stmt.setString(5, employee.getPostalCode());
            stmt.setString(6, employee.getTelephone());
            stmt.setInt(7, employee.getId());

            if (new PersonDAOImpl().update(employee)) {
                stmt.executeUpdate();
                return true;

            } else {
                return false;
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao alterar Employee! Erro: " + ex.getMessage());
            return false;

        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão com o BD! Erro: " + ex.getMessage());
            }
        }
    }

}
