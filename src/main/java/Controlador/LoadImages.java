/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import static Modelo.ScriptImagenes.updateUser;
import static Modelo.ScriptImagenes.updatePost;
import static Modelo.ScriptImagenes.updateProduct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessControlContext;
import static java.security.AccessController.getContext;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rodri
 */
@WebServlet(name = "LoadImages", urlPatterns = {"/loadimages"})
public class LoadImages extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, URISyntaxException {
        response.setContentType("text/html;charset=UTF-8");

        URL resource = getServletContext().getResource("/testimg/user1.jpg");
        File file = new File(resource.toURI());
        FileInputStream in = new FileInputStream(file);

        updateUser("ConductorMolon78", in);

        resource = getServletContext().getResource("/testimg/user2.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("MuchosCochesPocasNueces", in);

        resource = getServletContext().getResource("/testimg/user3.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("ElVerdeConBigoteDeCars", in);

        resource = getServletContext().getResource("/testimg/user4.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("conducirEsVivir21", in);

        resource = getServletContext().getResource("/testimg/user5.jpg");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("allCarsWYSTMN", in);

        resource = getServletContext().getResource("/testimg/user6.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("ConductorPromedio", in);

        resource = getServletContext().getResource("/testimg/user7.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("babyDriverGallego", in);

        resource = getServletContext().getResource("/testimg/user8.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("AdelantadoPorLaJessy", in);

        resource = getServletContext().getResource("/testimg/user9.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("ChoferSinAnimoDeLucro", in);

        resource = getServletContext().getResource("/testimg/user10.jpg");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("xXLealAlAsfaltoXx", in);

        resource = getServletContext().getResource("/testimg/user11.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("conduceCamiones24", in);

        resource = getServletContext().getResource("/testimg/user12.jpg");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("cocheNuevoQuienEs", in);

        resource = getServletContext().getResource("/testimg/user13.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("cochecitoLere", in);

        resource = getServletContext().getResource("/testimg/user14.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("CadenaVial", in);

        resource = getServletContext().getResource("/testimg/user15.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updateUser("ConducirYLoQueSurja", in);

        resource = getServletContext().getResource("/testimg/post1.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updatePost(1, in);

        resource = getServletContext().getResource("/testimg/post2.jpg");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updatePost(2, in);

        resource = getServletContext().getResource("/testimg/post3.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updatePost(3, in);

        resource = getServletContext().getResource("/testimg/post4.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updatePost(4, in);

        resource = getServletContext().getResource("/testimg/post5.jpeg");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updatePost(5, in);

        resource = getServletContext().getResource("/testimg/post6.jpg");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updatePost(6, in);

        resource = getServletContext().getResource("/testimg/post7.jpg");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updatePost(7, in);

        resource = getServletContext().getResource("/testimg/post8.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updatePost(8, in);

        resource = getServletContext().getResource("/testimg/post9.png");
        file = new File(resource.toURI());
        in = new FileInputStream(file);

        updatePost(9, in);




        in.close();

        /* PONER AQUI EL RESTO*/
        response.sendRedirect("profile");

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
            Logger.getLogger(LoadImages.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(LoadImages.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoadImages.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(LoadImages.class.getName()).log(Level.SEVERE, null, ex);
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
