/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.DAO;

import br.com.academiaonline.model.MuscleGroup;
import br.com.academiaonline.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author Diego
 */
public class MuscleGroupDAOImpl implements GenericDAO {

    private Connection conn;

    public MuscleGroupDAOImpl() throws Exception {
        try {

            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");

        } catch (Exception ex) {
            throw new Exception("Problemas ao se conectar com o Banco de Dados! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean save(Object object) {
        MuscleGroup muscleGroup = (MuscleGroup) object;

        PreparedStatement stmt = null;

        String sql = "INSERT INTO musclegroup(name_muscle_group, description_muscle_group) VALUES (?, ?);";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, muscleGroup.getName());
            stmt.setString(2, muscleGroup.getDescription());
            stmt.execute();
            return true;

        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Grupo Muscular! Erro: " + ex.getMessage());
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
