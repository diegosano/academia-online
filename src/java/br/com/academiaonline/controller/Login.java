/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.controller;

import br.com.academiaonline.DAO.PersonDAOImpl;
import br.com.academiaonline.model.Person;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Diego
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        if (request.getParameter("action").equals("login")) {

            if (!request.getParameter("email").equals("") && !request.getParameter("password").equals("")) {

                Person person = null;

                try {

                    PersonDAOImpl personDAO = new PersonDAOImpl();
                    person = personDAO.login(request.getParameter("email"), request.getParameter("password"));

                    if (person != null) {
                        HttpSession session = request.getSession(true);
                        session.setMaxInactiveInterval(600);
                        session.setAttribute("person", person);
                        session.setAttribute("welcome", "Seja bem-vindo(a) " + person.getName() + "!");
                        
                        if (person.getType().equalsIgnoreCase("E")) {
                            request.getRequestDispatcher("employee/index.jsp").forward(request, response);
                        } else if (person.getType().equalsIgnoreCase("U")) {
                            request.getRequestDispatcher("user/index.jsp").forward(request, response);
                        } else {
                            response.sendRedirect("home.html");
                        }
                    } else {
                        erro(request, response);
                    }

                } catch (Exception ex) {
                    System.out.println("Problemas ao logar Person! Erro: " + ex.getMessage());
                }

            } else {
                erro(request, response);
            }

        } else {
            HttpSession session = request.getSession(true);
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
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

    private void erro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("erro", "Email/Senha não válidos! Tente novamente!");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
