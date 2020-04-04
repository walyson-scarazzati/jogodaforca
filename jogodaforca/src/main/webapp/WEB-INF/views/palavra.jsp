<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="utf-8">
<title>Jogo da Forca</title>
<script type="text/javascript" src="resources/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script> 
<script type="text/javascript" src="resources/js/main.js"></script>

<link rel="stylesheet" href="resources/css/bootstrap.css" />
<link rel="stylesheet" href="resources/css/main.css" />
<link rel="stylesheet" href="resources/css/jquery-ui.css" />

</head>
<body>
	<section class="jumbotron">
		<h1>Jogo da Forca</h1>
		<p>Clique em começar jogo!</p>
		<p>
			<a id="novaPalavraButton" class="btn btn-primary btn-lg" >Começar jogo</a>
			<span id="status"></span>
		</p>
	</section>

	<section id="palavraPanel" class="panel panel-default hidden">
		<div class="panel-body">
			<span id="caption">Voc&ecirc; tem {10} tentativas. Adivinha estas {7} letras da palavra:</span> 
			<div id="adivinhacaoAtual">G__GLE</div>
			
			<div class="inputWrapper">
				<input id="letraInput" type="text" placeholder="Coloque uma letra aqui" maxlength="1"  onkeyup="this.value=this.value.replace(/[^A-zÀ-ú]/g,'')" />
				<a id="adivinhacaoButton" class="btn btn-primary">Adivinhe</a>
			</div>
		</div>
		
		<div>
			<p id="message"></p>
			<img id="hangmanImage" src="resources/img/0.png" alt="hangman image"/>
			<div><small>Esta imagem foi retirada de http://www.thegamegal.com/ e modificada para este aplicativo que foi inspirado em https://github.com/sonyseng/hangman/</small></div>
		</div>
	</section>

	<div hidden id="dialog-confirm" title="Fim de jogo!">
		<p>
			<span hidden></span>Terminou todas as palavras da categoria e dificuldade escolhidas
		</p>
	</div>

</body>
</html>
