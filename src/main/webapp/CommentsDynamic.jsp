<%-- 
    Document   : CommentsDynamic
    Created on : Apr 11, 2023, 1:19:05 PM
    Author     : Rodri
--%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="Modelo.Product"%>
<%@page import="Modelo.ProductDB"%>
<%@page import="Modelo.Vote"%>
<%@page import="Modelo.User"%>
<%@page import="Modelo.VoteDB"%>
<%@page import="Modelo.Comment"%>
<%@page import="Modelo.Post"%>
<%@page import="Modelo.UserDB"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>DriveBy</title>
        <link rel="stylesheet" type="text/css" href="../css/dbcomments.css">
    </head>
    <% HttpSession tmpSession = request.getSession();
        User userLogged = (User) tmpSession.getAttribute("userLogged");
    %>
    <body>
        <header>
            <nav>
                <ul class="left">
                    <li><a href="/DriveBy/main"><img class="static" src="../img/home.png"><img class="active" src="../img/home gif.gif" alt="Button 1"></a></li>
                    <li><a href="/DriveBy/dbpost.html"><img src="../img/post.png" alt="Button 2"></a></li>
                </ul>
                <ul class="right">
                    <li><a href="/DriveBy/profile/<%=userLogged.getUsername()%>"><img src="../img/account.png" alt="Button 3"></a></li>
                    <li><a href="/DriveBy/store"><img src="../img/shop.png" alt="Button 4"></a></li>
                </ul>
            </nav>
        </header>
        <main>
            <% Post actualPost = (Post) request.getAttribute("actualPost");%>
            <%

                String username = userLogged.getUsername();
                String path_u = "/DriveBy/img/uparrow.png";
                String path_d = "/DriveBy/img/downarrow.png";
                Vote voto = VoteDB.getVote(actualPost.getPostID(), username);

                if (voto != null) {
                    Boolean value = voto.isValue();
                    if (value == true) {
                        path_u = "/DriveBy/img/uparrow_active.png";

                    } else {
                        path_d = "/DriveBy/img/downarrow_active2.png";
                    }
                } else {

                }
            %>
            <div class="container">
                <div class="post">
 	  	<div class="autor">
                <a href="/DriveBy/profile/<%=actualPost.getUsername()%>" class="profile-link"><img src="data:image/png;base64,<%=UserDB.getUser(actualPost.getUsername()).getMedia()%>" alt="Profile Picture"></a>
		<a href="/DriveBy/profile/<%=actualPost.getUsername()%>" class="profile-link"><h4 id = "url"><%= actualPost.getUsername()%></h4></a>
	  </div>
                    <img class="post-img" src="data:image/png;base64,<%=actualPost.getMedia()%>" alt="Post Image">
                    <div class="post-text">
                        <h3><%= actualPost.getDesc()%></h3>
                        <p><%= actualPost.getLocation()%>   //    <%= actualPost.getDate().format( DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))%></p>
                    </div>
                    <div class="buttons">
                        <a id = "upvote" href="/DriveBy/positiveVote/comments/<%=String.valueOf(actualPost.getPostID())%>"><img src=<%=path_u%> alt="Button 5"></a>
                        <p>   <%=VoteDB.getVoteCount(actualPost.getPostID())%></p>
                        <a id="downvote" href="/DriveBy/negativeVote/comments/<%=String.valueOf(actualPost.getPostID())%>"><img src=<%=path_d%> alt="Button 3"></a>
                        &ThickSpace;

                        <%if (userLogged.getUsername().equalsIgnoreCase(actualPost.getUsername())) {%>
                        <a id="delete" href="/DriveBy/deletePost/comments/<%=String.valueOf(actualPost.getPostID())%>"> <img src="/DriveBy/img/share.png" alt="Button 7"></a>
                            <%};%>
                    </div>
                </div>
                <div class="commentWall">
                    <div class="autor">
                        <p></p>
                    </div>
                    <div class="comments">

                        <% ArrayList<Comment> tmpList = new ArrayList<>();
                            tmpList = (ArrayList<Comment>) request.getAttribute("commentList");

                            for (Comment x : tmpList) {
                                Product frame;
                                String frameURL;
                                try {
                                    frame = ProductDB.getProduct(UserDB.getUser(x.getUsername()).getFrame());
                                    frameURL = frame.getMedia();
                                } catch (Exception e) {
                                    frame = null;
                                    frameURL = null;
                                }
                        %>


                        <div class="comment">
                            <a href="/DriveBy/profile/<%=x.getUsername()%>" class="profile-link">
                                <div class="imgcontainer">
                                    <%if (frame != null) {%>
                                    <img class="inner-image" src="<%=ProductDB.getProduct(UserDB.getUser(x.getUsername()).getFrame()).getMedia()%>" alt="frame">
                                    <%};%>
                                    <img class="outer-image" src="data:image/png;base64,<%=UserDB.getUser(x.getUsername()).getMedia()%>" alt="Profile Picture">
                                </div>
                            </a>
                            <div class="comment-text">
                                <a href="/DriveBy/profile/<%=x.getUsername()%>" class="profile-link"><h4 id = "url"><%= x.getUsername()%></h4></a>
                                <p id = "comtxt"><%= x.getText()%></p>
                            </div>
                            <%if(userLogged.getUsername().equalsIgnoreCase(x.getUsername())){%>
                            <div class="delete-container">
                              <a id="delete" href="/DriveBy/deleteComment/<%=x.getUsername()%>/<%=String.valueOf(actualPost.getPostID())%>/<%=String.valueOf(x.getDate())%>"> <img src="/DriveBy/img/share.png" alt="delete button"></a>
                            </div>
                            <%};%>
                            
                        </div>
                        <%};%>

                    </div>
                    <div class="formcom">
                        <form method="post" action="/DriveBy/addcomment/<%=String.valueOf(actualPost.getPostID())%>">
                            <textarea id="Comment" name="commentText" rows="4" columns="1450" maxlength = "100" placeholder="Escribe un comentario..."></textarea>
                            <button type="submit">Comentar</button>
                        </form>
                    </div>
                    </main>
                    </body>
                    </html>
