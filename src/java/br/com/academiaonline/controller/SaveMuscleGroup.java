/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.controller;

import br.com.academiaonline.DAO.GenericDAO;
import br.com.academiaonline.DAO.MuscleGroupDAOImpl;
import br.com.academiaonline.model.MuscleGroup;
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
@WebServlet(name = "SaveMuscleGroup", urlPatterns = {"/SaveMuscleGroup"})
public class SaveMuscleGroup extends HttpServlet {

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

        MuscleGroup muscleGroup = new MuscleGroup();
        muscleGroup.setName(request.getParameter("namemusclegroup"));
        muscleGroup.setDescription(request.getParameter("descriptionmusclegroup"));

        try {

            GenericDAO dao = new MuscleGroupDAOImpl();

            if (request.getParameter("idmusclegroup").equals("")) {

                if (dao.save(muscleGroup)) {
                    message = "Grupo Muscular " + muscleGroup.getName() + " cadastrado com sucesso!";
                } else {
                    message = "Problemas ao cadastrar Grupo Muscular " + muscleGroup.getName() + "!";
                }

            } else {
                
                muscleGroup.setId(Integer.parseInt(request.getParameter("idmusclegroup")));
                
                if (dao.update(muscleGroup)) {
                    message = "Grupo Muscular " + muscleGroup.getName() + " alterado com sucesso!";
                } else {
                    message = "Problemas ao alterar Grupo Muscular " + muscleGroup.getName() + "!";
                }
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Grupo Muscular! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }

        request.setAttribute("return", message);
        request.getRequestDispatcher("musclegroup/save.jsp").forward(request, response);
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
