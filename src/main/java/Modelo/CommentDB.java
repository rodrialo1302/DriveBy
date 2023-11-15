/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Rodri
 */
public class CommentDB {

    public static Comment getComment(int postID, String username, LocalDateTime date) throws SQLException {

        Connection conn;

        String query = "SELECT " + "*" + " FROM \"COMMENT\" " + "WHERE USERNAME=? AND ID_POST=? AND DATE=?";

        conn = ConnectionPool.getInstance().getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setInt(2, postID);
        ps.setTimestamp(3, Timestamp.valueOf(date));

        ResultSet result = ps.executeQuery();

        if (result.next()) {
            Comment newComment = new Comment(username, postID, date, result.getString("TEXT"));
            ConnectionPool.getInstance().freeConnection(conn);
            return newComment;
        }
        ConnectionPool.getInstance().freeConnection(conn);
        throw new IllegalStateException("BBDD has no rows");

    }

    public static ArrayList<Comment> getCommentList(int postID) throws SQLException {

        Connection conn;

        ArrayList<Comment> commentList = new ArrayList<>();

        String query = "SELECT " + "*" + " FROM \"COMMENT\" " + "WHERE ID_POST=?";

        conn = ConnectionPool.getInstance().getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, postID);

        ResultSet result = ps.executeQuery();

        while (result.next()) {
            Comment tmpComment = new Comment(result.getString("USERNAME"), postID, result.getTimestamp("DATE").toLocalDateTime(), result.getString("TEXT"));
            commentList.add(tmpComment);

        }
        ConnectionPool.getInstance().freeConnection(conn);
        return commentList;

    }

    public static void addComment(Comment newComment) throws SQLException {

        int result;
        String query = "INSERT INTO \"COMMENT\"(USERNAME,ID_POST, DATE, TEXT) VALUES (?,?,?,?)";

        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, newComment.getUsername());
        ps.setInt(2, newComment.getPostID());
        ps.setTimestamp(3, Timestamp.valueOf(newComment.getDate()));
        ps.setString(4, newComment.getText());

        result = ps.executeUpdate();
        ConnectionPool.getInstance().freeConnection(conn);

    }

    public static int getCommentCount(int postID) throws SQLException {
        int total = 0;
        Connection conn;

        String query = "SELECT " + "*" + " FROM \"COMMENT\" " + "WHERE ID_POST=?";

        conn = ConnectionPool.getInstance().getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, postID);

        ResultSet result = ps.executeQuery();

        while (result.next()) {
            total++;
        }
        ConnectionPool.getInstance().freeConnection(conn);
        return total;

    }

    public static void deleteComment(int postID, String username, LocalDateTime date) throws SQLException, IOException {

        String query = "DELETE FROM \"COMMENT\" C WHERE C.ID_POST=? AND C.USERNAME=? AND C.DATE=?";
        Connection conn;
        conn = ConnectionPool.getInstance().getConnection();
        int result;

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, postID);
        ps.setString(2, username);
        ps.setTimestamp(3, Timestamp.valueOf(date));
        
        result = ps.executeUpdate();

        ConnectionPool.getInstance().freeConnection(conn);
    }

}
