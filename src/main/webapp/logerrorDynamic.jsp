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
		<title> Iniciar sesi�n </title>
		<link rel="stylesheet" type="text/css" href="css/dbdefaulterror.css">
	</head>
	<body>
		<main>
			<div class ="box">
                                <img src="img/spongebob-think.gif">
				<h1>Oops...</h1>
                                <h3>
                                <%if(!(request.getParameter("mensaje") == null)){
                                     out.println(request.getParameter("mensaje"));
                                     } else{
                                          out.println("Disculpa, esta p�gina no est� disponible en estos momentos ");
                                }%>
                                </h3>
                                <input type="button" value="Volver al men� login" onclick = "location.href='/DriveBy/dblogin.html';">
			</div>
			
		<br>
		</main>
	</body>
</html>
