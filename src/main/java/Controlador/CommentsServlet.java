/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rodri
 */
@WebServlet(name = "CommentsServlet", urlPatterns = {"/comments/*"})
public class CommentsServlet extends HttpServlet {

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
        int postID = Integer.parseInt(partsURL[partsURL.length - 1]);
        //PrintWriter out = response.getWriter();
        
        
        
        try{
            
            List<Comment> commentList = new ArrayList<>();
            try{
            commentList = CommentDB.getCommentList(postID);
            Comparator<Comment> comparator = (comm1, comm2) -> comm2.getDate()
                .compareTo(comm1.getDate());
            Collections.sort(commentList, comparator);
            }catch (Exception e){
                e.printStackTrace();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dbdefaulterror.html");
                dispatcher.forward(request, response);  
            }
            request.setAttribute("commentList", commentList); 
            
            
            
            try{
            request.setAttribute("actualPost", PostDB.getPost(postID));
            }catch (Exception e){
                e.printStackTrace();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dbdefaulterror.html");
                dispatcher.forward(request, response);  
            }
            
            
            
            
            RequestDispatcher dispatcher;
            dispatcher = getServletContext().getRequestDispatcher("/CommentsDynamic.jsp");
            dispatcher.forward(request, response);       
            
            
            
            
            /* TODO output your page here. You may use following sample code. */
        }catch (IOException | ServletException e){
            e.printStackTrace();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dbdefaulterror.html");
            dispatcher.forward(request, response);
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

}
