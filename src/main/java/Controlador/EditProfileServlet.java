/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import Modelo.User;
import Modelo.UserDB;
import java.io.FileInputStream;
import java.sql.SQLException;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author mario
 */
@WebServlet(name = "EditProfileServlet", urlPatterns = {"/edit"})
public class EditProfileServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User author = (User) session.getAttribute("userLogged");

        String mail = request.getParameter("mail");
        if (request.getParameter("mail") == "") {
            mail = author.getMail();
        }
        String completeName = request.getParameter("completeName");
        if (completeName.equals("")){
            completeName = author.getName();
        }
        String assocCar = request.getParameter("car");

        if (assocCar.equals("")){
            assocCar = author.getCar();
        }
        String bio = request.getParameter("bio");
        if (bio.equals("")){
            bio = author.getBio();
        }

        RequestDispatcher dispatcher = null;

        if (author == null) {
            dispatcher = getServletContext().getRequestDispatcher("/dblogin.html");
            dispatcher.forward(request, response);
        }
        author.setMail(mail);
        author.setName(completeName);
        author.setBio(bio);
        author.setCar(assocCar);
        try {
            UserDB.updateUser(author);
        } catch (SQLException e) {
            e.printStackTrace();
            dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
            dispatcher.forward(request, response);
        }

	   response.sendRedirect("/DriveBy/profile");

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
