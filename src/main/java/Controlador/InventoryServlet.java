/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Product;
import Modelo.ProductDB;
import Modelo.TransactionDB;
import Modelo.User;
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
@WebServlet(name = "InventoryServlet", urlPatterns = {"/inventory"})
public class InventoryServlet extends HttpServlet {

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
        try{
            
            ArrayList<Product> productList = new ArrayList<>();
            try{
            productList = ProductDB.getProductList();
            } catch (SQLException e){
                e.printStackTrace();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dbdefaulterror.html");
                dispatcher.forward(request, response);  
            }
            ArrayList<Product> purchasedList = new ArrayList<>();
            for (int i = 0; i < productList.size(); i++){
                try {
                    if (TransactionDB.getPurchasedTransaction(productList.get(i).getProductID(), author.getUsername()) != null)
                        purchasedList.add(productList.get(i));
                } catch (SQLException ex) {
                    Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            
            ArrayList<Product> frameList = new ArrayList<>();
            ArrayList<Product> picList = new ArrayList<>();
            
            for (int i = 0; i < purchasedList.size(); i++){
                if (purchasedList.get(i).getType() != 2)
                    frameList.add(purchasedList.get(i));
                else
                    picList.add(purchasedList.get(i));
            }
            request.setAttribute("frameList", frameList);
            request.setAttribute("picList", picList); 
            
            if (author.getFrame() > 0){
                try {
                    request.setAttribute("frameEquipped", ProductDB.getProduct(author.getFrame()));
                } catch (SQLException ex) {
                    Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
                   request.setAttribute("frameEquipped", null);
            
             //CAMBIAR
            //request.setAttribute("picEquipped", picList.get(0)); //CAMBIAR
            
           
            
            RequestDispatcher dispatcher;
            dispatcher = getServletContext().getRequestDispatcher("/InventoryDynamic.jsp");
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
