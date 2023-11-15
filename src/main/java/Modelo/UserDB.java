package Modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import static java.sql.Types.NULL;
import static java.sql.Types.INTEGER;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodri
 */
public class UserDB {

    public static User getUser(String username) throws SQLException, IOException {

        Connection conn;

        String query = "SELECT " + "*" + " FROM \"USER\" " + "WHERE USERNAME=?";

        conn = ConnectionPool.getInstance().getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username); //PUEDE SER 1

        ResultSet result = ps.executeQuery();

        if (result.next()) {
            User tmpUser = new User(username, result.getString("MAIL"), result.getString("PASSWORD"),
                    result.getString("NAME"), result.getString("CAR"), result.getString("BIO"), null,result.getInt("FRAME"), result.getInt("POINTS"));

            InputStream tmpBlob = result.getBinaryStream("MEDIA");
            if (tmpBlob != null) {
                tmpUser.setMedia(PostDB.convertBlobToB64(tmpBlob));
            }

            ConnectionPool.getInstance().freeConnection(conn);
            return tmpUser;

        }
        ConnectionPool.getInstance().freeConnection(conn);
        throw new IllegalStateException("BBDD has no rows");
    }

    public static Boolean isUser(String username) throws SQLException {

        Connection conn;

        String query = "SELECT " + "*" + " FROM \"USER\" " + "WHERE USERNAME=?";

        conn = ConnectionPool.getInstance().getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username); //PUEDE SER 1

        ResultSet result = ps.executeQuery();

        boolean isResult = result.next();
        ConnectionPool.getInstance().freeConnection(conn);
        return isResult;

    }

    public static void registerUser(User newUser, InputStream media) throws SQLException {
        int result;
        String query = "INSERT INTO \"USER\"(USERNAME,NAME,MAIL,PASSWORD,CAR,BIO,MEDIA,POINTS) VALUES (?,?,?,?,?,?,?,?)"; //No se que hacer aqui la verdad

        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, newUser.getUsername());
        ps.setString(2, newUser.getName());
        ps.setString(3, newUser.getMail());
        ps.setString(4, newUser.getPassword());
        ps.setString(5, newUser.getCar());
        ps.setString(6, newUser.getBio());
        ps.setBinaryStream(7, media);
        ps.setInt(8, newUser.getPoints());

        result = ps.executeUpdate();
        ConnectionPool.getInstance().freeConnection(conn);

    }

    public static void updateUser(User newUser) throws SQLException {
        int result;
        String query = "UPDATE \"USER\" SET MAIL=?,NAME=?,CAR=?,BIO=?,FRAME=? WHERE USERNAME=?";
        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, newUser.getMail());
        ps.setString(2, newUser.getName());
        ps.setString(3, newUser.getCar());
        ps.setString(4, newUser.getBio());
	   if(newUser.getFrame()==0){
		ps.setNull(5,INTEGER);
	   }else{
		ps.setInt(5,newUser.getFrame());
	   }
        ps.setString(6, newUser.getUsername());

        result = ps.executeUpdate();
        ConnectionPool.getInstance().freeConnection(conn);

    }
    
    
    public static void updatePic(User newUser, InputStream media) throws SQLException{
        int result;
        String query = "UPDATE \"USER\" SET MEDIA=? WHERE USERNAME=?";
        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setBinaryStream(1,media);
        ps.setString(2, newUser.getUsername());

        result = ps.executeUpdate();
        try {
            newUser.setMedia(PostDB.convertBlobToB64(media));
        } catch (IOException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionPool.getInstance().freeConnection(conn);
        
        
    }
    public static void updatePoints(User postAuthor,boolean value, int points) throws SQLException{
        int result;
        int newPoints=0;
        String query = "UPDATE \"USER\" SET POINTS=? WHERE USERNAME=?"; 
        
        if(!value){
            if(postAuthor.getPoints()>9){
                newPoints = postAuthor.getPoints() - points;
            }   
        }else{
            newPoints = postAuthor.getPoints() + points;
        }
        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, newPoints);
        ps.setString(2, postAuthor.getUsername());
        
        result = ps.executeUpdate();
        ConnectionPool.getInstance().freeConnection(conn);
    }
}
