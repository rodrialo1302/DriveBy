/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Post;
import Modelo.PostDB;
import Modelo.User;
import Modelo.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile/*"})
public class ProfileServlet extends HttpServlet {

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
        User tmpUser = null;
        if (partsURL.length == 4) {
            String userID = partsURL[partsURL.length - 1];
            
            try {
                tmpUser = UserDB.getUser(userID);
            } catch (SQLException ex) {
                Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            HttpSession session = request.getSession();
            tmpUser = (User) session.getAttribute("userLogged");
        }
        if (tmpUser == null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dbdefaulterror.html");
            dispatcher.forward(request, response);
        }

        try {

            ArrayList<Post> postList = new ArrayList<>();
            try {
                postList = PostDB.getUserPostList(tmpUser.getUsername());
            } catch (SQLException e) {
                e.printStackTrace();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
                dispatcher.forward(request, response);
            }
            request.setAttribute("userPostList", postList);
            request.setAttribute("profileAuthor", tmpUser);
            RequestDispatcher dispatcher;
            dispatcher = getServletContext().getRequestDispatcher("/profileDynamic.jsp");
            dispatcher.forward(request, response);

        } catch (IOException | ServletException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
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
