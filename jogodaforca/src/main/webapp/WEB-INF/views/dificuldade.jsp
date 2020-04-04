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
	<form:form action="${pageContext.request.contextPath}/dificuldadeRedirect/" modelAttribute="dificuldade" method="GET" >
		<div class="container form-group">
			<div class="row">
				<div class="col-md-6">
					<h1>Jogo da Forca</h1>
					Dificuldade:
					<form:select  id="dificuldade_id" name="dificuldade_id" cssClass="form-control" path="id" onchange="this.form.submit()">
						<form:option value="NONE" label="Escolha um nível de dificuldade" />
						<form:options items="${dificuldades}" itemLabel="descricao" itemValue="id"/>
					</form:select>
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>