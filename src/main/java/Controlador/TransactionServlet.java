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
@WebServlet(name = "TransactionServlet", urlPatterns = {"/transaction/*"})
public class TransactionServlet extends HttpServlet {

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
        int productID = Integer.parseInt(partsURL[partsURL.length - 1]);
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        User author = (User) session.getAttribute("userLogged");

        Product purchasedProduct = null;

        try {
            purchasedProduct = ProductDB.getProduct(productID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
            dispatcher.forward(request, response);
        }

        if (purchasedProduct == null) {
             // NO EXISTE PRODUCTO
             System.out.println("NO PRODUCTO");
            dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
            dispatcher.forward(request, response);
            return;
        }

        if (purchasedProduct.getPoints() > author.getPoints()) {
             //PUNTOS INSUFICIENTES
             System.out.println("PUNTOS INSUFICIENTES, PUNTOS ACTUALES:  "+ author.getPoints());
		   response.sendRedirect("/DriveBy/InventErrorDynamic.jsp");
            return;
        }
        Transaction newTransaction = null;
        
        try{
            newTransaction = TransactionDB.getPurchasedTransaction(productID, author.getUsername());
            } catch (SQLException s) {
                s.printStackTrace(); //SQL EXCEPTION
                dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
                dispatcher.forward(request, response);
                return;
            }
        if (newTransaction != null){
                 // YA COMPRADO
                dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
                dispatcher.forward(request, response);
                return;
        }
        
        

        try {
            UserDB.updatePoints(author, false, purchasedProduct.getPoints());
        } catch (SQLException ex) {
            Logger.getLogger(TransactionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        author.setPoints(author.getPoints() - purchasedProduct.getPoints());
        newTransaction = new Transaction(0, productID,
                author.getUsername(), LocalDateTime.now(), purchasedProduct.getPoints());
        
        try{
            TransactionDB.addTransaction(newTransaction);
            } catch (SQLException s) {
                s.printStackTrace(); //SQL EXCEPTION
                dispatcher = getServletContext().getRequestDispatcher("/dbstore.html");
                dispatcher.forward(request, response);
            }
        
        response.sendRedirect("/DriveBy/store");
        
        
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
