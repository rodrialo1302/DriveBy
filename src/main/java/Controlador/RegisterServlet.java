/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.*;
import static Modelo.ScriptImagenes.updateUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
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
 * @author mario
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 20,      // 20 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

public class RegisterServlet extends HttpServlet {

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
                Random random = new Random();
                int randomNumber = random.nextInt(9);
                String var = Integer.toString(randomNumber);

              URL resource = getServletContext().getResource("/img/eggImg" + var + ".png");
              File file = new File(resource.toURI());
              media = new FileInputStream(file); 
        }

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String completeName = request.getParameter("completeName");
        String mail = request.getParameter("mail");
        String password = LoginServlet.calculateMd5Hash(request.getParameter("password"));
        String confirmPassword = LoginServlet.calculateMd5Hash(request.getParameter("password"));
        String assocCar = request.getParameter("car");
        String bio = request.getParameter("bio");
        
		
        RequestDispatcher dispatcher = null;
        
        HttpSession session = request.getSession();
            if(username.length()==0){
                dispatcher = getServletContext().getRequestDispatcher("/registerValidation.jsp");
                dispatcher.forward(request, response);
                return; //ERROR CONTRASEÑA DIFERENTE
            }
            
            if(!password.equals(confirmPassword)){
                dispatcher = getServletContext().getRequestDispatcher("/registerValidation.jsp");
                dispatcher.forward(request, response);
                return; //ERROR CONTRASEÑA DIFERENTE
             }
            
            if(password.length()==0){

                dispatcher = getServletContext().getRequestDispatcher("/registerValidation.jsp");
                dispatcher.forward(request, response);
                return; //ERROR CONTRASEÑA DIFERENTE
             }

            User newUser = new User(null,null,null,null,null,null, null, 0,0);

            newUser.setUsername(username);
            newUser.setName(completeName);
            newUser.setMail(mail);
            newUser.setPassword(password);
            if(assocCar != ""){
                  newUser.setCar(assocCar);
            }
            if(bio != ""){
                  newUser.setBio(bio);
            }
                  
            try {
                if(UserDB.isUser(username) == false){
                    try{
                        UserDB.registerUser(newUser, media);
                    }catch(SQLException e){
                        e.printStackTrace();
                        return;
                    }
                    dispatcher = getServletContext().getRequestDispatcher("/login");
                    dispatcher.forward(request, response);
                }else{
                    request.setAttribute("existe", "si"); //NO CAMBIA SI ESTÁ CREADO EL USUARIO
                    dispatcher = getServletContext().getRequestDispatcher("/registerValidation.jsp");
                    dispatcher.forward(request, response);

                }
            } catch (SQLException e) {
                    e.printStackTrace();
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
            } catch (URISyntaxException ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
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
