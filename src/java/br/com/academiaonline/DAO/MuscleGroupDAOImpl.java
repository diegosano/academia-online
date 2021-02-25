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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        List<Object> result = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT mg.* FROM musclegroup mg ORDER BY mg.name_muscle_group;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                MuscleGroup muscleGroup = new MuscleGroup();
                muscleGroup.setId(rs.getInt("id_muscle_group"));
                muscleGroup.setName(rs.getString("name_muscle_group"));
                muscleGroup.setDescription(rs.getString("description_muscle_group"));
                result.add(muscleGroup);
            }

        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Grupo Muscular DAO! Erro: " + ex.getMessage());

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

        String sql = "DELETE FROM musclegroup WHERE id_muscle_group = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Problemas ao deletar Grupo Muscular! Erro: " + ex.getMessage());
            ex.printStackTrace();
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
    public Object findById(Integer idObject) {

        MuscleGroup muscleGroup = new MuscleGroup();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT mg.* FROM musclegroup mg WHERE mg.id_muscle_group = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            if (rs.next()) {
                muscleGroup = new MuscleGroup();
                muscleGroup.setId(rs.getInt("id_muscle_group"));
                muscleGroup.setName(rs.getString("name_muscle_group"));
                muscleGroup.setDescription(rs.getString("description_muscle_group"));
            }

        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar Grupo Muscular! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão com o BD! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return muscleGroup;
    }

    @Override
    public Boolean update(Object object) {
        MuscleGroup muscleGroup = (MuscleGroup) object;

        PreparedStatement stmt = null;

        String sql = "UPDATE musclegroup SET name_muscle_group = ?, description_muscle_group = ? WHERE id_muscle_group = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, muscleGroup.getName());
            stmt.setString(2, muscleGroup.getDescription());
            stmt.setInt(3, muscleGroup.getId());
            stmt.executeUpdate();
            return true;

        } catch (Exception ex) {
            System.out.println("Problemas ao alterar Grupo Muscular! Erro: " + ex.getMessage());
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
}
