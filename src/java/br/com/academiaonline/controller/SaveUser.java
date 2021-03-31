/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.controller;

import br.com.academiaonline.DAO.GenericDAO;
import br.com.academiaonline.DAO.UserDAOImpl;
import br.com.academiaonline.util.Conversions;
import br.com.academiaonline.model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Diego
 */
@WebServlet(name = "SaveUser", urlPatterns = {"/SaveUser"})
public class SaveUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws org.apache.commons.fileupload.FileUploadException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException {
        response.setContentType("text/html;charset=ISO-8859-1");

        User user = null;

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {

            user = new User();

            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setSizeMax(50 * 1024 * 1024); //50 Mb

            List items = upload.parseRequest(request);

            Iterator it = items.iterator();

            while (it.hasNext()) {
                FileItem fileItem = (FileItem) it.next();
                if (!fileItem.isFormField()) {
                    if (fileItem.getSize() != 0) {
                        user.setProfilePicture(fileItem.getInputStream());
                        user.setFileItem((int) fileItem.getSize());
                        
                    } else {
                        File file = new File("C:\\Users\\Diego\\Documents\\NetBeansProjects\\ProjetoAcademiaOnline\\web\\assets\\images\\avatar.webp");
                        System.out.println(file);
                        InputStream targetStream = new FileInputStream(file);
                        user.setProfilePicture(targetStream);
                        user.setFileItem((int) file.length());
                    }
                } else {
                    String dados = fileItem.getFieldName();
                    switch (dados) {
                        case "nameuser":
                            user.setName(fileItem.getString());
                            break;
                        case "birthdaydateuser":
                            user.setBirthdayDate(Conversions.convertDate(fileItem.getString().replace("-", "/")));
                            break;
                        case "cpfuser":
                            user.setCpf(fileItem.getString());
                            break;
                        case "emailuser":
                            user.setEmail(fileItem.getString());
                            break;
                        case "passworduser":
                            user.setPassword(fileItem.getString());
                            break;
                        case "iduser":
                            if (!fileItem.getString().equals("")) {
                                user.setId(Integer.parseInt(fileItem.getString()));
                            } else {
                                user.setId(null);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        String msg;
        user.setType("U");
        user.setRegistratioDate(new Date());
        try {
            GenericDAO dao = new UserDAOImpl();
            if (user.getId() == null) {
                if (dao.save(user)) {
                    msg = "User cadastrado com sucesso!";
                } else {
                    msg = "Problema ao cadastrar User!";
                }
            } else {
                if (dao.update(user)) {
                    msg = "User alterado com sucesso!";
                } else {
                    msg = "Problema ao alterar User!";
                }
            }

            request.setAttribute("return", msg);
            request.getRequestDispatcher("user/save.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problemas no Servlet ao salvar User! Erro: " + ex.getMessage());
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
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(SaveUser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(SaveUser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
