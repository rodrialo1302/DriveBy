package Controlador;

import Modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Rodri
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/main"})
public class MainServlet extends HttpServlet {

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
        try{
            
            List<Post> postList = new ArrayList<>();
            try{
            postList = PostDB.getPostList();
             Comparator<Post> comparator = (comm1, comm2) -> comm2.getDate()
                .compareTo(comm1.getDate());
            Collections.sort(postList, comparator);
            } catch (SQLException e){
                e.printStackTrace();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dbdefaulterror.html");
                dispatcher.forward(request, response);  
            }
            request.setAttribute("postList", postList);  
           
            
            RequestDispatcher dispatcher;
            dispatcher = getServletContext().getRequestDispatcher("/indexDynamic.jsp");
            dispatcher.forward(request, response);
            

            
            
            
        } catch (IOException | ServletException e){
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
