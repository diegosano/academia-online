/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.controller;

import br.com.academiaonline.DAO.GenericDAO;
import br.com.academiaonline.DAO.MuscleGroupDAOImpl;
import br.com.academiaonline.DAO.VideoLessonDAOImpl;
import br.com.academiaonline.model.MuscleGroup;
import br.com.academiaonline.model.VideoLesson;
import br.com.academiaonline.util.Conversions;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diego
 */
@WebServlet(name = "SaveVideoLesson", urlPatterns = {"/SaveVideoLesson"})
public class SaveVideoLesson extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");

        String message = null;

        VideoLesson videoLesson = new VideoLesson();
        videoLesson.setName(request.getParameter("namevideolesson"));
        videoLesson.setDescription(request.getParameter("descriptionvideolesson"));
        videoLesson.setLink(request.getParameter("linkvideolesson"));
        videoLesson.setPublicationDate(Conversions.convertDate(request.getParameter("datevideolesson").replace("-", "/")));
        videoLesson.setStatus(Boolean.parseBoolean(request.getParameter("status")));
        videoLesson.setMuscleGroup(new MuscleGroup(Integer.parseInt(request.getParameter("idmusclegroup"))));

        try {
            GenericDAO dao = new VideoLessonDAOImpl();
            if (dao.save(videoLesson)) {
                message = "Videoaula cadastrada com sucesso!";
            } else {
                message = "Problemas ao cadastrar Videoaula!";
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao salvar Videoaula! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        request.setAttribute("return", message);
        request.getRequestDispatcher("videolesson/save.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
