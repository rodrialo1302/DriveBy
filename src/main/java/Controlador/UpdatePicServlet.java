/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.User;
import Modelo.UserDB;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Rodri
 */
@WebServlet(name = "UpdatePicServlet", urlPatterns = {"/updatePic"})
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 20,      // 20 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class UpdatePicServlet extends HttpServlet {

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
        FileInputStream media = null;
        Part filePart = request.getPart("upload");
        if(!"".equals(filePart.getSubmittedFileName())){
            String uuid = UUID.randomUUID().toString();
            String fileNameProvided = filePart.getSubmittedFileName();
            String fileName = UUID.randomUUID().toString() 
                    + fileNameProvided.substring(fileNameProvided.lastIndexOf("."));
            String fileCompletePath = System.getProperty("catalina.base")+ "/persist/img/" + fileName;
            //String fileCompletePath = "D:/Downloads/apache-tomcat-9.0.73/persist/img" + fileName;
            for (Part part : request.getParts()) {
              part.write(fileCompletePath);
            }

            media = new FileInputStream(fileCompletePath);
    }else{
                response.sendRedirect("/DriveBy/inventory");
			 return;
        }
        
        HttpSession session = request.getSession();
        User author = (User) session.getAttribute("userLogged");
        if (author == null)
        {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dblogin.html");
                dispatcher.forward(request, response);    
        }
                
        
        String username = author.getUsername();
        
        try {
            UserDB.updatePic(author, media);
        } catch (SQLException ex) {
            Logger.getLogger(UpdatePicServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("/DriveBy/inventory");
        
        
        
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
