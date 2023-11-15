<%-- 
    Document   : registerDynamic
    Created on : Apr 21, 2023, 7:18:07 PM
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="java.lang.*"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DriveBy</title>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="css/dbregister.css">
    </head>
    <body>
        <center>
            

            
            
            
            <%
                if((request.getParameter("username").length()) == 0){
                        
            %>
                <jsp:forward page="regerrorDynamic.jsp">
                <jsp:param  name="mensaje" value = "Parece ser que no has introducido ningun nombre de usuario" />
                </jsp:forward>
            <%
                 }
            %>
            
            
            <% 
   
                if(request.getAttribute("correctPasswd") == "no"){
                        
            %>
                
                <jsp:forward page="logerrorDynamic.jsp">
                <jsp:param  name="mensaje" value = "Parece ser que la contraseña no coincide con el nombre de usuario" />
                </jsp:forward>
            <%
                 }
            %>
            
            
            
            <% 
   
                if(request.getAttribute("existe") == "si"){
                        
            %>
                
                <jsp:forward page="regerrorDynamic.jsp">
                <jsp:param  name="mensaje" value = "Parece ser que ese nombre de usuario ya existe" />
                </jsp:forward>
            <%
                 }
            %>
            
            <%
   
                if(request.getAttribute("existe") == "no"){
                        
            %>
                
                <jsp:forward page="logerrorDynamic.jsp">
                <jsp:param  name="mensaje" value = "Parece ser que no existe ningun usuario con ese nombre" />
                </jsp:forward>
            <%
                 }
            %>
            

            
            <% 
            if((request.getParameter("password").length()) == 0){
                        
            %>
                <jsp:forward page="regerrorDynamic.jsp">
                <jsp:param  name="mensaje" value = "Parece ser que no has introducido ninguna contraseña" />
                </jsp:forward>
            <%
                 }
            %>
            

            
            <%
                if(request.getParameter("password") != request.getParameter("confirmPassowrd")){
                        
            %>
                <jsp:forward page="regerrorDynamic.jsp">
                <jsp:param  name="mensaje" value = "Parece ser que has introducido contraseñas diferentes" />
                </jsp:forward>
            <%
                 }
            %>
  
            
                        

        </center>
    </body>
</html>
