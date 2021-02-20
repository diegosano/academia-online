/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.DAO;

import br.com.academiaonline.model.VideoLesson;
import br.com.academiaonline.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego
 */
public class VideoLessonDAOImpl implements GenericDAO {

    private Connection conn;

    public VideoLessonDAOImpl()  throws Exception {
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
