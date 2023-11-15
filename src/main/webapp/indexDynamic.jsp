<%-- 
    Document   : indexDynamic
    Created on : 10 abr. 2023, 19:35:12
    Author     : Rodri
--%>
<%@page import="Modelo.Vote"%>
<%@page import="Modelo.User"%>
<%@page import="Modelo.CommentDB"%>
<%@page import="Modelo.VoteDB"%>
<%@page import="Modelo.UserDB"%>
<%@page import="Modelo.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<html>
<head>
	<title>DriveBy</title>
	<link rel="stylesheet" type="text/css" href="css/dbcss.css">
</head>
<body>
    
    <%
         
            HttpSession tmpSession = request.getSession();
            User user = (User) tmpSession.getAttribute("userLogged");
            String username;
            Boolean sesionIniciada = false;
            if(user == null){
                 username = null;
                 sesionIniciada = false;
            }
            else{
                 username = user.getUsername();
                 sesionIniciada = true;
            }
      %>
	<header>
		<nav>   
                        <% if(sesionIniciada){%>
                            <ul class="left">
                                    <li><a href="/DriveBy/main"><img class="static" src="img/home.png"><img class="active" src="img/home gif.gif" alt="Button 1"></a></li>
                                    <li><a href="dbpost.html"><img src="img/post.png" alt="Button 2"></a></li>
                            </ul>
                        <%}else{%>
                            <ul class="left">
                                    <li><a href="/DriveBy/main"><img class="static" src="img/home.png"><img class="active" src="img/home gif.gif" alt="Button 1"></a></li>
                                    <li><a href="dblogin.html"><img src="img/post.png" alt="Button 2"></a></li>
                            </ul>
                        
                        <%}%>
                        
                        
                        <% if(sesionIniciada){%>
                            <ul class="right">
				<li><a href="/DriveBy/profile/<%=user.getUsername()%>"><img src="img/account.png" alt="Button 3"></a></li>
                                <li><a href="/DriveBy/store"><img src="img/shop.png" alt="Button 4"></a></li>

                            </ul>
                        <%}else{%>
                            <ul class="right">
				<li><a href="/DriveBy/dbregister.html"><img src="img/register.png" alt="Button 3"></a></li>
                                <li><a href="/DriveBy/dblogin.html"><img src="img/login.png" alt="Button 4"></a></li>

                            </ul>
                        <%}%>

		</nav>
	</header>
  <main>
      
      <%ArrayList<Post> tmpList = new ArrayList<>();
      tmpList = (ArrayList<Post>) request.getAttribute("postList");
      for(Post x:tmpList){%>

      
      <%
                String path_u = "img/uparrow.png";
                String path_d = "img/downarrow.png";
                if(sesionIniciada){
                    Vote voto = VoteDB.getVote(x.getPostID(),username);
                    if(voto != null){
                         Boolean value = voto.isValue();
                         if(value == true){
                              path_u = "img/uparrow_active.png";
                         }else{
                              path_d = "img/downarrow_active2.png";
                         }
                    }else{
                         //("NULL");
                    }
                }
            
        %>
        
        <% if(sesionIniciada){%>
             <div class="box" onclick = "location.href='comments/<%=String.valueOf(x.getPostID())%>'" >                
        <%}else{%>
            <div class="box" onclick = "location.href='dblogin.html'" >
        <%}%>
		 <div class = "boximg">
			<img class ='minimg' src="data:image/png;base64,<%=x.getMedia()%>" alt="Image 1">
		 </div>
		 <div class="content">
			<p class = "minp"><%=x.getDesc()%></p>
			<div class="ubi">
				<p><%=x.getLocation()%></p>
					
				<div class="buttons">
                                    <p><%= x.getDate().format( DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))%></p>
                                    		
					<%if(sesionIniciada){%>
                                            <a href="/DriveBy/profile/<%=x.getUsername()%>" class="profile-link"> <img src="data:image/png;base64,<%=UserDB.getUser(x.getUsername()).getMedia()%>" alt="Button 7" class="profile-image"><%=x.getUsername()%></a>
                                            <img src="img/comments.png" alt="Button 2" onclick="location.href='comments/<%=String.valueOf(x.getPostID())%>'">
                                            <p><%= CommentDB.getCommentCount(x.getPostID())%></p>
                                            <a id = "upvote" href="/DriveBy/positiveVote/main/<%=String.valueOf(x.getPostID())%>"><img src="<%=path_u%>" alt="Button 5"></a>
                                            <p><%= VoteDB.getVoteCount(x.getPostID())%></p>
                                            <a id = "downVote" href="/DriveBy/negativeVote/main/<%=String.valueOf(x.getPostID())%>"><img src="<%=path_d%>" alt="Button 3"></a>
                                            <%if(x.getUsername().equalsIgnoreCase(user.getUsername())){%>
                                                   <a id="delete" href="/DriveBy/deletePost/main/<%=String.valueOf(x.getPostID())%>"><img src="img/share.png" alt="Button 7"></a>                                            <%};%>
                                        <%}else{%>
                                            <a href="dblogin.html" class="profile-link"> <img src="data:image/png;base64,<%=UserDB.getUser(x.getUsername()).getMedia()%>" alt="Button 7" class="profile-image"><%=x.getUsername()%></a>
                                            <img src="img/comments.png" alt="Button 2" onclick="location.href='dblogin.html'">
                                            <p><%= CommentDB.getCommentCount(x.getPostID())%></p>
                                            <a id = "upvote" href="dblogin.html"><img src="img/uparrow.png" alt="Button 5"></a>
                                            <p><%= VoteDB.getVoteCount(x.getPostID())%></p>
                                            <a id = "downVote" href="dblogin.html"><img src="img/downarrow.png" alt="Button 3"></a>
                                        <%}%>

                                        
				</div> 
			</div>
		</div>
	</div>
	<%};%>
  </main>
</body>
</html>
