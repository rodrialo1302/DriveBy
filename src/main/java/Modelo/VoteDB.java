package Modelo;

import java.sql.*;

/**
 *
 * @author Rodri
 */
public class VoteDB {
    
    public static Vote getVote (int postID, String username)throws SQLException{

        Connection conn;
        
        String query = "SELECT " + "*" + " FROM \"VOTE\" " + "WHERE USERNAME=? AND ID_POST=?";
        
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setInt(2, postID);
        
        ResultSet result = ps.executeQuery();
		
        
        
        if (result.next()){
           Vote newVote =  new Vote(result.getBoolean("VALUE"), postID, username);
           ConnectionPool.getInstance().freeConnection(conn);
            
           return newVote;
                    
        }
        ConnectionPool.getInstance().freeConnection(conn);
        return null;         
        
    }
    
    public static void addVote(Vote newVote) throws SQLException{
        
		int result;
		String query = "INSERT INTO \"VOTE\"(VALUE,USERNAME,ID_POST) VALUES (?,?,?)";

        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, newVote.isValue());
            ps.setString(2, newVote.getUsername());
            ps.setInt(3, newVote.getPostID());
		  
		  result = ps.executeUpdate();
		ConnectionPool.getInstance().freeConnection(conn);

    }        
    public static void deleteVote(Vote oldVote) throws SQLException{
        int result;
        String query = "DELETE "+ " FROM \"VOTE\" " + "WHERE USERNAME=? AND ID_POST=?";
        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, oldVote.getUsername());
        ps.setInt(2, oldVote.getPostID());
        result = ps.executeUpdate();
		ConnectionPool.getInstance().freeConnection(conn);
    }  
        
       
    
    public static int getVoteCount(int postID) throws SQLException{
        int total = 0;
         Connection conn;
        
        String query = "SELECT " + "*" + " FROM \"VOTE\" " + "WHERE ID_POST=?";
        
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, postID);
        
        ResultSet result = ps.executeQuery();
		
        
        
        while (result.next()){
            total = total + (result.getBoolean("VALUE")? 1 : -1);
        }
        ConnectionPool.getInstance().freeConnection(conn);
        return total;
        
    }
    
}
