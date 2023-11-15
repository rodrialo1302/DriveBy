package Modelo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Base64;
/**
 *
 * @author Rodri
 */
public class PostDB {
    
    public static ArrayList<Post> getPostList () throws SQLException, IOException{
        Connection conn;
        
        ArrayList<Post> postList = new ArrayList<>();
        
        String query = "SELECT" + "*" + " FROM \"POST\" ";
        
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
        
        ResultSet result = ps.executeQuery();       
		
        
        while (result.next()){
            Post tmpPost = new Post(result.getInt("ID_POST"),result.getString("USERNAME"),result.getString("LOCATION"),
                    result.getString("DESCRIPTION"),result.getString("ALERT_TYPE"),result.getTimestamp("DATE").toLocalDateTime(),
                    null);
            
            InputStream tmpBlob = result.getBinaryStream("MEDIA");
             if (tmpBlob != null)
                tmpPost.setMedia(convertBlobToB64(tmpBlob));
            //Blob blob = result.getBlob("MEDIA");
            
            postList.add(tmpPost);
            
            
        }
        ConnectionPool.getInstance().freeConnection(conn);
        return postList;
        
    }
    
    
    public static Post getPost(int postID)throws SQLException, IOException {
        
        Connection conn;
        
        String query = "SELECT " + "*" + " FROM \"POST\" " + "WHERE ID_POST=?";
        
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, postID); //PUEDE SER 1
        
        ResultSet result = ps.executeQuery(); 
		
        
        
        if (result.next()){
           Post tmpPost = new Post(result.getInt("ID_POST"),result.getString("USERNAME"),result.getString("LOCATION"),
                    result.getString("DESCRIPTION"),result.getString("ALERT_TYPE"),result.getTimestamp("DATE").toLocalDateTime(),
                    null);
            
            InputStream tmpBlob = result.getBinaryStream("MEDIA");
             if (tmpBlob != null)
                tmpPost.setMedia(convertBlobToB64(tmpBlob));
             
             ConnectionPool.getInstance().freeConnection(conn);
             return tmpPost;
             
        }
        ConnectionPool.getInstance().freeConnection(conn);
        throw new IllegalStateException("BBDD has no rows");   
    }
    
    public static void addPost (Post newPost, FileInputStream media) throws SQLException {
        int result;

        Connection conn;
                String query = "INSERT INTO \"POST\"(USERNAME,LOCATION, DESCRIPTION,ALERT_TYPE,DATE,MEDIA) VALUES (?,?,?,?,?,?)";
        conn = ConnectionPool.getInstance().getConnection();
        
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newPost.getUsername());
            ps.setString(2, newPost.getLocation());
            ps.setString(3, newPost.getDesc());
            ps.setString(4, newPost.getAlertType());
            ps.setTimestamp(5, Timestamp.valueOf(newPost.getDate()));
            ps.setBinaryStream(6, media);
		  
		  result = ps.executeUpdate();
		ConnectionPool.getInstance().freeConnection(conn);

    }
    public static void deletePost (int postID, String username) throws SQLException, IOException{
        
        String query = "DELETE FROM POST P WHERE P.ID_POST=? AND P.USERNAME=?";
        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();
        int result;
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, postID);
        ps.setString(2, username);
        result = ps.executeUpdate();
        
        ConnectionPool.getInstance().freeConnection(conn);
    }
    
    public static ArrayList<Post> getUserPostList (String username) throws SQLException, IOException{
        Connection conn;
        
        ArrayList<Post> postList = new ArrayList<>();
        
        String query = "SELECT" + "*" + " FROM \"POST\" WHERE USERNAME=?";
        
        conn = ConnectionPool.getInstance().getConnection();
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        
        ResultSet result = ps.executeQuery();       
		
        
        while (result.next()){
            Post tmpPost = new Post(result.getInt("ID_POST"),result.getString("USERNAME"),result.getString("LOCATION"),
                    result.getString("DESCRIPTION"),result.getString("ALERT_TYPE"),result.getTimestamp("DATE").toLocalDateTime(),
                    null);
            
            InputStream tmpBlob = result.getBinaryStream("MEDIA");
             if (tmpBlob != null)
                tmpPost.setMedia(convertBlobToB64(tmpBlob));
            //Blob blob = result.getBlob("MEDIA");
            
            postList.add(tmpPost);
        }
        
        ConnectionPool.getInstance().freeConnection(conn);
        return postList;
        
    } 
    
    
    
    
    protected static String convertBlobToB64(InputStream inputStream)throws SQLException, IOException{
        //InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);                  
            }            
            byte[] imageBytes = outputStream.toByteArray();
            String base64Media = Base64.getEncoder().encodeToString(imageBytes);          
            
            inputStream.close();
            outputStream.close();
            
            
            return base64Media;
            //tmpPost.setMedia(base64Media);
        
        
    }
    
    
    
    
}