<%-- 
    Document   : errorDynamic
    Created on : Apr 21, 2023, 7:38:11 PM
    Author     : mario
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title> Error </title>
		<link rel="stylesheet" type="text/css" href="css/dbdefaulterror.css">
	</head>
	<body>
		<main>
			<div class ="box">
                                <img src="img/spongebob-think.gif">
				<h1>Oops...</h1>
                                <h3>
                                <%if(!(request.getParameter("mensaje") == null)){
                                     out.println("Vaya! No tienes suficientes puntos");
                                     } else{
                                          out.println("Vaya! No tienes suficientes puntos");
                                }%>
                                </h3>
                                <input type="button" value="Volver a la tienda" onclick = "location.href='/DriveBy/store';">
			</div>
			
		<br>
		</main>
	</body>
</html>
