<%-- 
    Document   : StoreDynamic
    Created on : May 4, 2023, 10:05:55 AM
    Author     : Rodri
--%>

<%@page import="Modelo.UserDB"%>
<%@page import="Modelo.TransactionDB"%>
<%@page import="Modelo.User"%>
<%@page import="Modelo.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DriveBy</title>
        <link rel="stylesheet" type="text/css" href="css/dbstorecss.css">
    </head>
    
    <% HttpSession tmpSession = request.getSession();
      User userLogged = (User) tmpSession.getAttribute("userLogged");
      %>
    <body>
        <header>
            <nav>
                <ul class="left">
                    <li><a href="/DriveBy/main"><img class="static" src="img/home.png"><img class="active" src="img/home gif.gif" alt="Button 1"></a></li>
                    <li><a href="dbpost.html"><img src="img/post.png" alt="Button 2"></a></li>
                </ul>
                <ul class="right">
                    <li><a href="/DriveBy/profile/<%=userLogged.getUsername()%>"><img src="img/account.png" alt="Button 3"></a></li>
                    <li><a href="/DriveBy/store"><img src="img/shop.png" alt="Button 4"></a></li>

                </ul>
            </nav>
        </header>
         <main>








            <div class="box-container">
                <h1>Tienda</h1>
                <div class="autor">
                <a href="/DriveBy/profile/<%=userLogged.getUsername()%>" class="profile-link"><img src="data:image/png;base64,<%=UserDB.getUser(userLogged.getUsername()).getMedia()%>" alt="Profile Picture"></a>
		<h3 id = "url"><%= userLogged.getUsername()%>&emsp;</h3>
                <h3 id = "url"> ||&emsp;SALDO&emsp;=&emsp; </h3>
                <h3 id = "url"><%= userLogged.getPoints()%>&nbsp;</h3>
                <img class="moneda" src="/DriveBy/img/coin.png">

                </div>
                <br>
                <h2>Marcos</h2>		
                <div class="box">
                    <%ArrayList<Product> frameList = new ArrayList<>();
                        frameList = (ArrayList<Product>) request.getAttribute("frameList");
                        for (Product x : frameList) {%>

                    <div class="upload-image-container">
                        <% if (TransactionDB.getPurchasedTransaction(x.getProductID(), userLogged.getUsername()) == null) {%>
                        <a href="/DriveBy/transaction/<%=x.getProductID()%>"><img class="item-image" src="<%=x.getMedia()%>"></a>
                        <figcaption><%=x.getPoints()%><img class="coin-logo" src="img/coin1.png" alt="coin-logo"></figcaption>
                            <% } else {%>
                        <img class="purchased" src="<%=x.getMedia()%>">
                        <figcaption>COMPRADO</figcaption>   
                            <%}%>
                    </div>
                    <%};%>
                </div>
                <h2>Marcos Animados</h2>
                <div class="box">
                    <%ArrayList<Product> animFrameList = new ArrayList<>();
                        animFrameList = (ArrayList<Product>) request.getAttribute("animFrameList");
                        for (Product y : animFrameList) {%>

                    <div class="upload-image-container">
                        <% if (TransactionDB.getPurchasedTransaction(y.getProductID(), userLogged.getUsername()) == null) {%>
                        <a href="/DriveBy/transaction/<%=y.getProductID()%>"><img class="item-image" src="<%=y.getMedia()%>"></a>
                        <figcaption><%=y.getPoints()%><img class="coin-logo" src="img/coin1.png" alt="coin-logo"></figcaption>
                            <% } else {%>
                        <img class="purchased" src="<%=y.getMedia()%>">
                        <figcaption>COMPRADO</figcaption>   
                            <%}%>
                    </div>
                    <%};%>
                </div>	
                <h2>Avatares</h2>
                <div class="box">
                    <%ArrayList<Product> picList = new ArrayList<>();
                    picList = (ArrayList<Product>) request.getAttribute("picList");
                    for (Product z : picList) {%>

                    <div class="upload-image-container">
                        <% if (TransactionDB.getPurchasedTransaction(z.getProductID(), userLogged.getUsername()) == null) {%>
                        <a href="/DriveBy/transaction/<%=z.getProductID()%>"><img class="item-image" src="<%=z.getMedia()%>"></a>
                        <figcaption><%=z.getPoints()%><img class="coin-logo" src="img/coin1.png" alt="coin-logo"></figcaption>
                            <% } else {%>
                        <img class="purchased" src="<%=z.getMedia()%>">
                        <figcaption>COMPRADO</figcaption>   
                            <%}%>
                    </div>
                    <%};%>
                </div>
            </div>
            <br>
            <br>
        </main>
    </body>
</html>