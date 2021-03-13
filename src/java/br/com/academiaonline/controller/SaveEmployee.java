/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.controller;

import br.com.academiaonline.DAO.EmployeeDAOImpl;
import br.com.academiaonline.DAO.GenericDAO;
import br.com.academiaonline.model.Employee;
import br.com.academiaonline.util.Conversions;
import java.io.File;
import java.io.IOException;
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
@WebServlet(name = "SaveEmployee", urlPatterns = {"/SaveEmployee"})
public class SaveEmployee extends HttpServlet {

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
            throws ServletException, IOException, FileUploadException {
        response.setContentType("text/html;charset=ISO-8859-1");

        Employee employee = null;

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {

            employee = new Employee();

            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setSizeMax(50 * 1024 * 1024); //50 Mb

            List items = upload.parseRequest(request);

            Iterator it = items.iterator();

            while (it.hasNext()) {
                FileItem fileItem = (FileItem) it.next();
                if (!fileItem.isFormField()) {
                    try {
                        fileItem.write(new File("C:\\Users\\Diego\\Documents\\NetBeansProjects\\ProjetoAcademiaOnline\\web\\employee\\curriculum\\" + (new File(fileItem.getName())).getName()));
                    } catch (Exception ex) {
                        System.out.println("Problemas ao pegar o nome do arquivo! Erro: " + ex.getMessage());
                    }
                    System.out.println(fileItem.getName());
                    employee.setCurriculumVitae("curriculum/" + fileItem.getName());
                    System.out.println("O arquivo enviado foi: "  + employee.getCurriculumVitae());
                } else {
                    String dados = fileItem.getFieldName();
                    switch (dados) {
                        case "name":
                            employee.setName(fileItem.getString());
                            break;
                        case "birthday-date":
                            employee.setBirthdayDate(Conversions.convertDate(fileItem.getString().replace("-", "/")));
                            break;
                        case "cpf":
                            employee.setCpf(fileItem.getString());
                            break;
                        case "email":
                            employee.setEmail(fileItem.getString());
                            break;
                        case "password":
                            employee.setPassword(fileItem.getString());
                            break;
                        case "address":
                            employee.setAddress(fileItem.getString());
                            break;
                        case "city":
                            employee.setCity(fileItem.getString());
                            break;
                        case "state":
                            employee.setState(fileItem.getString());
                            break;
                        case "postal-code":
                            employee.setPostalCode(fileItem.getString());
                            break;
                        case "telephone":
                            employee.setTelephone(fileItem.getString());
                            break;
                        case "id":
                            if (!fileItem.getString().equals("")) {
                                employee.setId(Integer.parseInt(fileItem.getString()));
                            } else {
                                employee.setId(null);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        String msg;
        employee.setType("E");
        try {
            GenericDAO dao = new EmployeeDAOImpl();
            if (employee.getId() == null) {
                if (dao.save(employee)) {
                    msg = "Employee cadastrado com sucesso!";
                } else {
                    msg = "Problema ao cadastrar Employee!";
                }
            } else {
                if (dao.update(employee)) {
                    msg = "Employee alterado com sucesso!";
                } else {
                    msg = "Problema ao alterar Employee!";
                }
            }

            request.setAttribute("return", msg);
            request.getRequestDispatcher("employee/save.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problemas no Servlet ao salvar Employee! Erro: " + ex.getMessage());
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
            Logger.getLogger(SaveEmployee.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SaveEmployee.class.getName()).log(Level.SEVERE, null, ex);
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
