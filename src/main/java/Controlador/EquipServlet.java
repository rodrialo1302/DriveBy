/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Product;
import Modelo.ProductDB;
import Modelo.Transaction;
import Modelo.TransactionDB;
import Modelo.User;
import Modelo.UserDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
@WebServlet(name = "EquipServlet", urlPatterns = {"/equip/*"})
public class EquipServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException, URISyntaxException {
        response.setContentType("text/html;charset=UTF-8");
        String uri = request.getRequestURI();
        String[] partsURL = uri.split("/");
        int productID = Integer.parseInt(partsURL[partsURL.length - 1]);
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        User author = (User) session.getAttribute("userLogged");
        
	   if (productID == -2) { //QUITAR PIC
              FileInputStream media = null;          
		    Random random = new Random();
              int randomNumber = random.nextInt(9);
              String var = Integer.toString(randomNumber);
              URL resource = getServletContext().getResource("/img/eggImg" + var + ".png");
              File file = new File(resource.toURI());
              media = new FileInputStream(file); 
		    UserDB.updatePic(author, media);
		    response.sendRedirect("/DriveBy/inventory");
		    return;

        }

        if (productID == -1){ // QUITAR MARCO
            author.setFrame(0);
            try {
                UserDB.updateUser(author);
			 response.sendRedirect("/DriveBy/inventory");
			 return;
            } catch (SQLException ex) {
			 ex.printStackTrace();
			 response.sendRedirect("/DriveBy/inventory");
			 return; 
            }
	    }
            
        

        Product newItem = null;

        try {
            newItem = ProductDB.getProduct(productID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
            dispatcher.forward(request, response);
        }
        
        
        Transaction newTransaction = null;
        try{
            newTransaction = TransactionDB.getPurchasedTransaction(productID, author.getUsername());
            } catch (SQLException s) {
                s.printStackTrace(); //SQL EXCEPTION
                dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
                dispatcher.forward(request, response);
            }
        if (newTransaction == null){
                 // NO LO HA COMPRADO -- LADRON INFORMATICO
                dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
                dispatcher.forward(request, response);   
        }
        
        
        if (newItem.getType() == 2){
            
            try {
                InputStream media = new URL(newItem.getMedia()).openStream();
                UserDB.updatePic(author, media);
            } catch (SQLException ex) {
                Logger.getLogger(EquipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            author.setFrame(productID);
            try {
                UserDB.updateUser(author);
            } catch (SQLException ex) {
                Logger.getLogger(EquipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
	    try {
		    processRequest(request, response);
	    } catch (SQLException ex) {
		    Logger.getLogger(EquipServlet.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (URISyntaxException ex) {
		    Logger.getLogger(EquipServlet.class.getName()).log(Level.SEVERE, null, ex);
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
	    } catch (SQLException ex) {
		    Logger.getLogger(EquipServlet.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (URISyntaxException ex) {
		    Logger.getLogger(EquipServlet.class.getName()).log(Level.SEVERE, null, ex);
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
