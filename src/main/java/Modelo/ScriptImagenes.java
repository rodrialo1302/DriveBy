/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Rodri
 */
public class ScriptImagenes {
    
        public static void updateUser (String newUser, FileInputStream media) throws SQLException {
        int result;
        String query = "UPDATE \"USER\" SET MEDIA=? WHERE USERNAME=?";
        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
            ps.setBinaryStream(1, media);
            ps.setString(2, newUser);
		  
            result = ps.executeUpdate();
        ConnectionPool.getInstance().freeConnection(conn);
    }
    
        
        public static void updatePost (int postid, FileInputStream media) throws SQLException {
        int result;
        String query = "UPDATE \"POST\" SET MEDIA=? WHERE ID_POST=?";
        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
            ps.setBinaryStream(1, media);
            ps.setInt(2, postid);
            result = ps.executeUpdate();
        ConnectionPool.getInstance().freeConnection(conn);
    }
        
        public static void updateProduct(int productid, FileInputStream media) throws SQLException {
            int result;
        String query = "UPDATE \"PRODUCT\" SET MEDIA=? WHERE ID_PRODUCT=?";
        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
            ps.setBinaryStream(1, media);
            ps.setInt(2, productid);
            result = ps.executeUpdate();
        ConnectionPool.getInstance().freeConnection(conn);            
            
        }
    
    
}
      //updateUser("ConductorMolon78", in);
      /*
      file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("MuchosCochesPocasNueces", in);
      
            file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("ElVerdeConBigoteDeCars", in);
      
            file = new File(System.getProperty("user.dir" + "img/user3."));
      in = new FileInputStream(file);  
      
      
      updateUser("conducirEsVivir21", in);
      
            file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("allCarsWhenYouSpellTheManName", in);
      
            file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("ConductorPromedio", in);
      
            file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("babyDriverGallego", in);
      
            file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("AdelantadoPorLaJessy", in);
      
            file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("ChoferSinAnimoDeLucro", in);
      
            file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("|LealAlAsfalto|", in);
      
            file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("conduceCamiones24", in);
      
            file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("cocheNuevoQuienEs?", in);
      
            file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("cochecitoLere", in);
      
            file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("CadenaVial", in);
      
            file = new File(System.getProperty("user.dir" + "img/user2"));
      in = new FileInputStream(file);  
      
      
      updateUser("ConducirYLoQueSurja", in);
      
      
      
      
      
      
      
      
      file = new File(System.getProperty("user.dir" + "img/post1"));
      in = new FileInputStream(file);  
      
      
      updatePost(1, in);
        
      
       file = new File(System.getProperty("user.dir" + "img/post1"));
      in = new FileInputStream(file);  
      
      
      updatePost(2, in);
      
            file = new File(System.getProperty("user.dir" + "img/post1"));
      in = new FileInputStream(file);  
      
      
      updatePost(3, in);
      
            file = new File(System.getProperty("user.dir" + "img/post1"));
      in = new FileInputStream(file);  
      
      
      updatePost(4, in);
      
            file = new File(System.getProperty("user.dir" + "img/post1"));
      in = new FileInputStream(file);  
      
      
      updatePost(5, in);
      
            file = new File(System.getProperty("user.dir" + "img/post1"));
      in = new FileInputStream(file);  
      
      
      updatePost(6, in);
      
            file = new File(System.getProperty("user.dir" + "img/post1"));
      in = new FileInputStream(file);  
      
      
      updatePost(7, in);
      
            file = new File(System.getProperty("user.dir" + "img/post1"));
      in = new FileInputStream(file);  
      
      
      updatePost(8, in);
      
            file = new File(System.getProperty("user.dir" + "img/post1"));
      in = new FileInputStream(file);  
      
      
      updatePost(9, in);
      
      */
        