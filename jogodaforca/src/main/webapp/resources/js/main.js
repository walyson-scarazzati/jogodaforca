$(function() {

	var novaPalavraButton = $('#novaPalavraButton'), 
	adivinhacaoButton = $('#adivinhacaoButton'), 
	letraInputBox = $('#letraInput'), 
	caption = $('#caption'), 
	adivinhacaoAtual = $('#adivinhacaoAtual'), 
	palavraPanel = $('#palavraPanel'), 
	message = $('#message'), 
	hangmanImage = $('#hangmanImage'), 
	status = $('#status'), 
	palavraAtual = {}, 
	quantidadesJogos = 0, 
	palavrasCertas = 0;

	novaPalavraButton.click(criarNovaPalavra);
	adivinhacaoButton.click(fazerAdivinhacao);
	
	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname
				.indexOf("/", 2));
	}

	function criarNovaPalavra() {
		$.ajax({
			url : "palavra",
			type : 'POST'
		}).done(function(palavra) {
			if(palavra){
			palavraAtual = palavra;
			renderStatus();
			renderPalavra(palavra);
			palavraPanel.removeClass('hidden');
			message.text('');
			}else{
				  $( '#dialog-confirm' ).dialog({
				      resizable: false,
				      height: "auto",
				      width: 400,
				      modal: true,
				      buttons: {
				        "Começar outro jogo": function() {
							window.location = getContextPath()
							+ "/";
				          $( this ).dialog( "close" );
				        }

				      }
				    });
			}
		});
	}

	function fazerAdivinhacao() {
		$.ajax({
			url : evalTempl('palavra/{id}?letra={letra}', {
				id : palavraAtual.id,
				letra : letraInputBox.val()
			}),
			type : 'PUT'
		}).done(function(palavra) {
			renderPalavra(palavra);
			checkStatus(palavraAtual, palavra);
			palavraAtual = palavra;
			renderStatus();
			letraInputBox.focus();
			letraInputBox.val('');
		});
	}

	function checkStatus(palavraAtual, palavra) {
		if (palavra.status === "WON") {
			message.html("Voc&ecirc; ganhou!");
			palavrasCertas++;
			quantidadesJogos++;
		} else if (palavra.status === "LOST") {
			message.html('Voc&ecirc; perdeu!');
			message.overrideMimeType('text/html; charset=UTF-8');
			quantidadesJogos++;
		} else if (palavra.tentativasRestantes === palavraAtual.tentativasRestantes) {
			message.text('Bom trabalho, continue!');
		} else if (palavra.tentativasRestantes < palavraAtual.tentativasRestantes) {
			message.text('Continue tentando, mas pense com cuidado!');
		}
	}

	function renderPalavra(palavra) {
		caption.html(evalTempl("Voc&ecirc; tem {tentativasRestantes} tentativas. Adivinhe esta {tamanhoPalavra} letras da palavra: ", palavra));
		adivinhacaoAtual.text(palavra.adivinhacaoAtual);
		hangmanImage.attr('src', evalTempl('resources/img/{imageIndex}.png', {
			imageIndex : 10 - palavra.tentativasRestantes
		}));
	}

	function renderStatus() {
		status.text(evalTempl(
				'{palavrasCertas} jogo(s) ganho(s). {quantidadesJogos} jogo(s) realizados.', {
					palavrasCertas : palavrasCertas,
					quantidadesJogos : quantidadesJogos
				}));
	}

	function evalTempl(str, context) {
		return str.replace(/\{(.*?)\}/g, function(match, key) {
			return context[key];
		});
	}
});
