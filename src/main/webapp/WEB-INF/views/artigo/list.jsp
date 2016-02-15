<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function confirmarExclusao(){
	var a = confirm("Deseja realizar a exclusão?");
	if(a){
		return true
	}else{
		return false
	}
}
</script>
<title>lista de artigos</title>
</head>
<body>
	
	<c:if test="${not empty sucesso}">
		<c:out value="${sucesso}"></c:out>
	</c:if>
	
	<c:url var="url" value="/gerenciador/form"/>
	<a href="${url}">Novo Artigo</a>
	
	<table>
		
		<tr>
			<td>Titulo</td>
			<td>Ações</td>
		</tr>
		
		
		<c:forEach items="${artigos}" var="artigo">
		<tr>
			<td>${artigo.titulo}</td>
			<td>
				<a href="<c:url value="/gerenciador/edit/${artigo.id}"/>">Editar</a>
				<a href="<c:url value="/gerenciador/remove/${artigo.id}"/>" onclick="if(!confirmarExclusao()) return false;">Excluir</a>
			</td>
		</tr>	
		</c:forEach>
		
	</table>	
</body>
</html>		
		