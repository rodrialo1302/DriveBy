/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Comment;
import Modelo.CommentDB;
import Modelo.PostDB;
import Modelo.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rodri
 */
@WebServlet(name = "DeleteCommentServlet", urlPatterns = {"/deleteComment/*"})
public class DeleteCommentServlet extends HttpServlet {

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
        String uri = request.getRequestURI();
        String[] partsURL = uri.split("/");
        int postID = Integer.parseInt(partsURL[partsURL.length - 2]);
        String username = partsURL[partsURL.length - 3];
        LocalDateTime date = LocalDateTime.parse(partsURL[partsURL.length - 1]);
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        User author = (User) session.getAttribute("userLogged");
        
        Comment oldComment = null;
        try{
            CommentDB.getComment(postID, username, date);
        }catch(SQLException e){
                    e.printStackTrace();
                    dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
                    dispatcher.forward(request, response);
                }
        
        
        String commentAuthor = null;
        if(author.getUsername().equalsIgnoreCase(username)){
            try{
                CommentDB.deleteComment(postID, username, date);
                //response.sendRedirect("/DriveBy/profile");
            }catch(SQLException e){
                e.printStackTrace();
                dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
                dispatcher.forward(request, response);
            }
        }

        
        response.sendRedirect("/DriveBy/comments/" + postID);

        
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
