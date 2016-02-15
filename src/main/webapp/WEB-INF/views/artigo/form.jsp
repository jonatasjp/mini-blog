<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inserir Artigos</title>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	
	<form:form action="${spring:mvcUrl('AC#save').build()}" method="post"
		commandName="artigo">
		
		<div>
			<form:hidden path="id" />
		</div>
		
		<div>
			<label for="titulo">Titulo:</label>
			<form:input path="titulo" />
			<form:errors path="titulo" />
		</div>
		<br/>
		<div>
			<label for="texto">Texto:</label>
			<form:textarea path="texto" rows="5" cols="20" />
			<form:errors path="texto" />
		</div>
		<br/>		
		<div>
			<input type="submit" value="Enviar">
		</div>

	</form:form>
</body>
</html>
