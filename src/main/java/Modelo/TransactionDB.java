/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Rodri
 */
public class TransactionDB {
    
    public static Transaction getTransaction (int transactionID) throws SQLException {
        
        Connection conn;
        
        String query = "SELECT " + "*" + " FROM \"TRANSACTION\" " + "WHERE ID_TRANSACTION=?";
        
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, transactionID);
        
        ResultSet result = ps.executeQuery();
		
        
        
        if (result.next()){
            Transaction newTransaction = new Transaction(transactionID, result.getInt("ID_PRODUCT"), 
                    result.getString("USERNAME"), result.getTimestamp("DATE").toLocalDateTime(), result.getInt("POINTS"));
            ConnectionPool.getInstance().freeConnection(conn);
            return newTransaction;
        }
        ConnectionPool.getInstance().freeConnection(conn);
        return null;          
        
    }  

    public static Transaction getPurchasedTransaction (int productID, String username) throws SQLException {
        
        Connection conn;
        
        String query = "SELECT " + "*" + " FROM \"TRANSACTION\" " + "WHERE ID_PRODUCT=? AND USERNAME = ?";
        
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, productID);
        ps.setString(2, username);
        
        ResultSet result = ps.executeQuery();
		
        
        
        if (result.next()){
            Transaction newTransaction = new Transaction(result.getInt("ID_TRANSACTION"), productID, 
                    username, result.getTimestamp("DATE").toLocalDateTime(), result.getInt("POINTS"));
            ConnectionPool.getInstance().freeConnection(conn);
            return newTransaction;
        }
        ConnectionPool.getInstance().freeConnection(conn);
        return null;         
        
    }          
    
        public static void addTransaction(Transaction newTransaction) throws SQLException{
        
		int result;
		String query = "INSERT INTO \"TRANSACTION\"(ID_PRODUCT,USERNAME, DATE, POINTS) VALUES (?,?,?,?)";

        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, newTransaction.getProductID());
            ps.setString(2, newTransaction.getUsername());
            ps.setTimestamp(3, Timestamp.valueOf(newTransaction.getDate()));
            ps.setInt(4, newTransaction.getPoints());
		  
		  result = ps.executeUpdate();
		ConnectionPool.getInstance().freeConnection(conn);

    }        
    
    
    
}
