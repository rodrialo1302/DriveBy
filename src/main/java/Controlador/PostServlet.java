package Controlador;

import Modelo.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodri
 */
@WebServlet(name = "PostServlet", urlPatterns = {"/post"})
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 20,      // 20 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class PostServlet extends HttpServlet {

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
            throws ServletException, IOException, URISyntaxException {
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
            String alertType = request.getParameter("alert_type");
            URL resource = null;
            switch(alertType){
                case "Congestion de Trafico":
                    resource = getServletContext().getResource("/img/trafficlogo.png");
                    break;
                case "Obstaculo(Accidente,Obras,Desvio)":
                    resource = getServletContext().getResource("/img/obstaclelogo.png");
                    break;
                case "Control":
                    resource = getServletContext().getResource("/img/controllogo.png");
                    break;
                case "Aviso":
                    resource = getServletContext().getResource("/img/warninglogo.png");
                    break;
            }
            File file = new File(resource.toURI());
            media = new FileInputStream(file); 
        }
        
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        User author = (User) session.getAttribute("userLogged");
        if (author == null)
        {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dblogin.html");
                dispatcher.forward(request, response);    
        }
                
        
        String username = author.getUsername();
        //String username = "a";
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String alertType = request.getParameter("alert_type");
        LocalDateTime date = LocalDateTime.now();
        
        
        RequestDispatcher dispatcher = null;
       
        
        Post newPost = new Post(0,username,location,description,alertType,date, null);
        try {
            if(!newPost.getDesc().equals("") && !newPost.getLocation().equals("")){
			PostDB.addPost(newPost,media);
		  } 
        } catch (SQLException e) {
            e.printStackTrace();

            return;
        }
	  
        response.sendRedirect("main");
        

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
        } catch (URISyntaxException ex) {
            Logger.getLogger(PostServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (URISyntaxException ex) {
            Logger.getLogger(PostServlet.class.getName()).log(Level.SEVERE, null, ex);
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
