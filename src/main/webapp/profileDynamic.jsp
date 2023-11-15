<%@page import="Modelo.ProductDB"%>
<%@page import="Modelo.Product"%>
<%@page import="Modelo.Vote"%>
<%-- 
    Document   : profileDynamic
    Created on : 11 abr. 2023, 21:20:44
    Author     : mario
--%>
<%@page import="Modelo.VoteDB"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="Modelo.CommentDB"%>
<%@page import="Modelo.Post"%>
<%@page import="Modelo.User"%>
<%@page import="Modelo.UserDB"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    HttpSession tmpSession = request.getSession();
    User userLogged = (User) tmpSession.getAttribute("userLogged");
    User tmpUser = (User) request.getAttribute("profileAuthor");
    Product frame;
    String frameURL;
    try{
    frame = ProductDB.getProduct(tmpUser.getFrame());
    frameURL = frame.getMedia();
    }catch(Exception e){
         frame= null;   
         frameURL = null;
    }
     
%>
<html>
<head>
	<title>DriveBy</title>
	<link rel="stylesheet" type="text/css" href="/DriveBy/css/dbprofile.css">
</head>

<body>
	<header>
		<nav>
			<ul class="left">
				<li><a href="/DriveBy/main"><img class="static" src="/DriveBy/img/home.png"><img class="active" src="/DriveBy/img/home gif.gif" alt="Button 1"></a></li>
				<li><a href="/DriveBy/dbpost.html"><img src="/DriveBy/img/post.png" alt="Button 2"></a></li>
			</ul>
			<ul class="right">
				<li><a href="/DriveBy/profile/<%=userLogged.getUsername()%>"><img src="/DriveBy/img/account.png" alt="Button 3"></a></li>
                                <li><a href="/DriveBy/store"><img src="/DriveBy/img/shop.png" alt="Button 4"></a></li>
			</ul>
		</nav>
	</header>
	<div class="maindiv">
	<div class="container">
            <%if(frame!=null){%>
                <div class="imgcontainer">
                    
                    <img class="inner-image"  src="data:image/png;base64,<%=tmpUser.getMedia()%>" alt="Placeholder Image">
                    <img class="outer-image"  src="<%=frameURL%>">
                    
                </div>
                <%}else{%>
                    <div class="imgcontainer-width">
                    <img class="inner-image"  src="data:image/png;base64,<%=tmpUser.getMedia()%>" alt="Placeholder Image">
                </div>
                <%};%>
		<div class="text">
			<br><br>
                        <h1><%=tmpUser.getUsername()%></h1> <label>&emsp; <%=tmpUser.getPoints()%><img class="moneda" src="/DriveBy/img/coin.png"></label>
			<h2><%=tmpUser.getName()%></h2>
			<h6><%=tmpUser.getCar()%></h6>
			<div class = "bio">
				<p id ="bio"><%=tmpUser.getBio()%></p>
			</div>
			<div class="edit">
                               <%if(userLogged.getUsername().equalsIgnoreCase(tmpUser.getUsername())){%>
                               <input type="button" value="Cerrar Sesion" onclick = "location.href='/DriveBy/logout'">

				<a href="/DriveBy/inventory" class="image-button"><img class="store-button" src="/DriveBy/img/inventory.png"></a>
				<a href="/DriveBy/dbchangeprofile.html"><img class="cp-button" src="/DriveBy/img/editpf2.png"></a>
                               <a href="/DriveBy/loadimages">Cargar imagenes</a>
                                <%}%>
                                
			</div>
		</div>



	</div>
	<br>
	<h2>Publicaciones:</h2>
	<div>
	</div>
<%
 
      String username = userLogged.getUsername();
      
      ArrayList<Post> tmpList = new ArrayList<>();
      tmpList = (ArrayList<Post>) request.getAttribute("userPostList");
  
      for(Post x:tmpList){%>
      
           <%
           String path_u = "/DriveBy/img/uparrow.png";
           String path_d = "/DriveBy/img/downarrow.png";
           Vote voto = VoteDB.getVote(x.getPostID(),username);
           
           if(voto != null){
                Boolean value = voto.isValue();
                if(value == true){
                     path_u = "/DriveBy/img/uparrow_active.png";
 
                }else{
                     path_d = "/DriveBy/img/downarrow_active2.png";
                }
           }else{
           }
        %>
      <div class="box" onclick = "location.href='/DriveBy/comments/<%=String.valueOf(x.getPostID())%>'" >
		 <div class = "boximg">
			<img class ='minimg' src="data:image/png;base64,<%=x.getMedia()%>" alt="Image 1">
		 </div>
		 <div class="content">
			<p class = "minp"><%=x.getDesc()%></p>
			<div class="ubi">
				<p><%=x.getLocation()%></p>
					
				<div class="buttons">
                                    <p><%= x.getDate().format( DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))%></p>
					<a href="/DriveBy/profile/<%=x.getUsername()%>" class="profile-link"> <img src="data:image/png;base64,<%=UserDB.getUser(x.getUsername()).getMedia()%>" alt="Button 7" class="profile-image"><%=x.getUsername()%></a>		
					<img src="/DriveBy/img/comments.png" alt="Button 2" onclick="location.href='comments/<%=String.valueOf(x.getPostID())%>'">
					<p><%= CommentDB.getCommentCount(x.getPostID())%></p>
					<a id = "upvote" href="/DriveBy/positiveVote/profile/<%=String.valueOf(x.getPostID())%>"><img src=<%=path_u%> alt="Button 5"></a>
					<p><%= VoteDB.getVoteCount(x.getPostID())%></p>
                                        <a id ="downvote" href="/DriveBy/negativeVote/profile/<%=String.valueOf(x.getPostID())%>"><img src=<%=path_d%> alt="Button 3"></a>
                                        <%if(userLogged.getUsername().equalsIgnoreCase(tmpUser.getUsername())){%>
                                        <a id="delete" href="/DriveBy/deletePost/profile/<%=String.valueOf(x.getPostID())%>"> <img src="/DriveBy/img/share.png" alt="Button 7"></a>
                                        <%};%>
                                   </div> 
			</div>
		</div>
	</div>
	<%};%>
</body>
</html>
