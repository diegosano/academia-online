/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.DAO;

import br.com.academiaonline.model.MuscleGroup;
import br.com.academiaonline.model.VideoLesson;
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
public class VideoLessonDAOImpl implements GenericDAO {

    private Connection conn;

    public VideoLessonDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");

        } catch (Exception ex) {
            throw new Exception("Problemas ao se conectar com o Banco de Dados! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean save(Object object) {

        VideoLesson videoLesson = (VideoLesson) object;

        System.out.println(videoLesson.getName());
        System.out.println(videoLesson.getDescription());
        System.out.println(videoLesson.getLink());
        System.out.println(videoLesson.getPublicationDate());
        System.out.println(videoLesson.getStatus());
        System.out.println(videoLesson.getMuscleGroup().getId());

        PreparedStatement stmt = null;
        String sql = "INSERT INTO videolesson(name_video_lesson, description_video_lesson, link_video_lesson, publication_date_video_lesson, status_video_lesson, id_muscle_group) VALUES (?, ?, ?, ?, ?, ?);";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, videoLesson.getName());
            stmt.setString(2, videoLesson.getDescription());
            stmt.setString(3, videoLesson.getLink());
            stmt.setDate(4, new java.sql.Date(videoLesson.getPublicationDate().getTime()));
            stmt.setBoolean(5, videoLesson.getStatus());
            stmt.setInt(6, videoLesson.getMuscleGroup().getId());
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar Videoaula! Erro: " + ex.getMessage());
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

        String sql = "SELECT vl.*, mg.name_muscle_group "
                + "FROM videolesson vl, musclegroup mg "
                + "WHERE vl.id_muscle_group = mg.id_muscle_group "
                + "ORDER BY vl.publication_date_video_lesson DESC;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                VideoLesson videoLesson = new VideoLesson();
                videoLesson.setId(rs.getInt("id_video_lesson"));
                videoLesson.setName(rs.getString("name_video_lesson"));
                videoLesson.setDescription(rs.getString("description_video_lesson"));
                videoLesson.setLink(rs.getString("link_video_lesson"));
                videoLesson.setPublicationDate(rs.getDate("publication_date_video_lesson"));
                videoLesson.setStatus(rs.getBoolean("status_video_lesson"));
                videoLesson.setMuscleGroup(new MuscleGroup(rs.getInt("id_muscle_group"), rs.getString("name_muscle_group")));
                result.add(videoLesson);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Videoaulas! Erro: " + ex.getMessage());
            ex.printStackTrace();
            
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão com o BD! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        return result;
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
