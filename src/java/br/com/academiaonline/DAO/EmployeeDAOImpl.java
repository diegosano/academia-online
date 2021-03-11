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
                + "curriculum_employee, "
                + "address_employee, "
                + "city_employee, "
                + "state_employee, "
                + "postal_code_employee, "
                + "telephone_employee, "
                + "id_person "
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
