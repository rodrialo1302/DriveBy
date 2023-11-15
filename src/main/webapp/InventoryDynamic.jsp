<%-- 
    Document   : InventoryDynamic
    Created on : May 16, 2023, 11:39:20 AM
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
                <h1>Mi inventario:</h1>
               
                <div class="autor">
                <a href="/DriveBy/profile/<%=userLogged.getUsername()%>" class="profile-link"><img src="data:image/png;base64,<%=UserDB.getUser(userLogged.getUsername()).getMedia()%>" alt="Profile Picture"></a>
		<h3 id = "url"><%= userLogged.getUsername()%>&emsp;</h3>
                <h3 id = "url"> ||&emsp;SALDO&emsp;=&emsp; </h3>
                <h3 id = "url"><%= userLogged.getPoints()%>&nbsp;</h3>
                <img class="moneda" src="/DriveBy/img/coin.png">

                </div>
                
                <h2>Mis Marcos&nbsp; <button type="submit" onclick="location.href='/DriveBy/equip/-1';">Quitar Marco</button></h2> 
                <div class ="box-general"> 
                    
                <div class="equip-box">
                <% Product frameEquipped = (Product) request.getAttribute("frameEquipped");
                    if(frameEquipped != null){
                    if (frameEquipped.getProductID() != 0){%>
                    <div class="upload-image-container-equipped">
                        <img class="item-image" src="<%=frameEquipped.getMedia()%>">
                        <figcaption>EQUIPADO</figcaption> 
                    </div>  
                    <%}%>
                    
                    <%if (frameEquipped.getProductID() == 0){%>
  
                    <%}%>
                    <%}else{%>
                        <div class="upload-image-container-equipped">
                        <img class="item-image" src="/DriveBy/img/DefBck.png">
                        <figcaption>EQUIPADO</figcaption> 
                        </div>
                    <%}%>                        

                       
                                            
                </div>        


                <div class="box">
                    <%ArrayList<Product> frameList = new ArrayList<>();
                        frameList = (ArrayList<Product>) request.getAttribute("frameList");
                        for (Product x : frameList) {%>
                    <div class="upload-image-container">
                        <a href="/DriveBy/equip/<%=x.getProductID()%>"><img class="item-image" src="<%=x.getMedia()%>"></a>
                        <figcaption>EQUIPAR</figcaption>
                    </div>
                    <%};%>
                </div>
                </div>
            <h2>Mis Avatares&nbsp; <button type="submit" onclick="location.href='/DriveBy/equip/-2';">Quitar Avatar</button></h2> 
            <div class="box-general">
            <div class="equip-box">
                <div class="upload-image-container-equipped">
                    <img class="item-image" src="data:image/png;base64,<%=UserDB.getUser(userLogged.getUsername()).getMedia()%>"">
                    <figcaption>IMG ACTUAL</figcaption>   
                </div>    
            </div>

            <div class="box">
                <div class="upload-image-container">
                    <form method="POST" id="uploadimg" enctype="multipart/form-data" action="updatePic">
                        <label for="upload">
                            <img class="item-image" src="img/uploadpfp.png"/>
                        </label>
                        <input type="file" id="upload" name="upload" accept = "image/*" hidden>
                        <figcaption><button type="submit">Actualizar</button></figcaption>   

                    </form>
                </div>

                <%ArrayList<Product> picList = new ArrayList<>();
                    picList = (ArrayList<Product>) request.getAttribute("picList");
                    for (Product x : picList) {%>
                    <div class="upload-image-container">
                        <a href="/DriveBy/equip/<%=x.getProductID()%>"><img class="item-image" src="<%=x.getMedia()%>"></a>
                        <figcaption>EQUIPAR</figcaption>
                </div>

                <%};%>
            </div>
            </div>
        </div>





    </div>
    <br>
    <br>
</main>
</body>
</html>