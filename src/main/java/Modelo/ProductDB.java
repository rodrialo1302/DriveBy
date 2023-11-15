package Modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Rodri
 */
public class ProductDB {
    
    public static Product getProduct(int productID) throws SQLException, IOException{
        
        Connection conn;
        
        String query = "SELECT " + "*" + " FROM \"PRODUCT\" " + "WHERE ID_PRODUCT=?";
        
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, productID); //PUEDE SER 1
        
        ResultSet result = ps.executeQuery(); 
		
        
        
        if (result.next()){
           Product tmpProduct = new Product(productID, result.getInt("POINTS"), 
                   result.getString("NAME"), result.getString("MEDIA"), result.getInt("TYPE"));
             
             ConnectionPool.getInstance().freeConnection(conn);
             return tmpProduct;
             
        }
        ConnectionPool.getInstance().freeConnection(conn);
        return null;  
    }
    
        public static ArrayList<Product> getProductList () throws SQLException, IOException{
        Connection conn;
        
        ArrayList<Product> productList = new ArrayList<>();
        
        String query = "SELECT" + "*" + " FROM \"PRODUCT\" ";
        
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
        
        ResultSet result = ps.executeQuery();       
		
        
        while (result.next()){
           Product tmpProduct = new Product(result.getInt("ID_PRODUCT"), result.getInt("POINTS"), 
                   result.getString("NAME"), result.getString("MEDIA"), result.getInt("TYPE"));
            
            
            productList.add(tmpProduct);
            
            
        }
        ConnectionPool.getInstance().freeConnection(conn);
        return productList;
        
    }
        
    public static void addProduct (Product newProduct) throws SQLException {
        int result;

        Connection conn;
                String query = "INSERT INTO \"PRODUCT\"(POINTS,NAME,MEDIA) VALUES (?,?,?,?)";
        conn = ConnectionPool.getInstance().getConnection();
        
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, newProduct.getPoints());
            ps.setString(2, newProduct.getName());
            ps.setString(3, newProduct.getMedia());
            ps.setInt(4, newProduct.getType());
		  
		  result = ps.executeUpdate();
		ConnectionPool.getInstance().freeConnection(conn);

    }    
            
    
}
