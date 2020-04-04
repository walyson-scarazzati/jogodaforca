<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="resources/js/jquery-1.12.4.js"></script>

<link rel="stylesheet" href="resources/css/bootstrap.css" />
<link rel="stylesheet" href="resources/css/main.css" />
<title>Jogo da forca</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/categoriaRedirect/" modelAttribute="categoria" method="GET" >
		<div class="container form-group">
			<div class="row">
				<div class="col-md-6">
					<h1>Jogo da Forca</h1>
					Categoria:
					<form:select  id="categoria_id" name="categoria_id" cssClass="form-control" path="id" onchange="this.form.submit()">
						<form:option value="NONE" label="Escolha uma categoria" />
						<form:options items="${categorias}" itemLabel="descricao" itemValue="id"/>
					</form:select>
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>