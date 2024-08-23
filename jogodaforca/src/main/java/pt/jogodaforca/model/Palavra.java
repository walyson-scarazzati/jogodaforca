package pt.jogodaforca.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pt.jogodaforca.convertEnum.StatusEnum;

/**
 * Classe implementa entidade
 * @author Walyson
 * @version 1.0
 * @since   2019-12-05 
 */
@Getter
@Setter
@Entity
@ToString
@Table(name="palavra")
public class Palavra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;

	@ManyToMany(cascade = { CascadeType.ALL })
	private List<Categoria> categoria;

	@ManyToOne
	private Dificuldade dificuldade;

	@Enumerated(EnumType.ORDINAL)
	private StatusEnum status;
	
	@Transient
	private int tamanhoPalavra;

	@Transient
	private int tentativasRestantes;

	@Transient
	private String adivinhacaoAtual;

	public Palavra() {
		super();
	}

	public Palavra(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
		this.tentativasRestantes = 10;
		this.adivinhacaoAtual = descricao.replaceAll(".", "_");
		this.tamanhoPalavra = descricao.length();
		this.status = StatusEnum.PLAYABLE;
	}


	// Se a letra que colocou existir na palavra e não foi escrita
	public void marcarPalavra(String letter) {
		if (descricao.contains(letter) && !adivinhacaoAtual.contains(letter)) {
			atualizaAdivinhacaoAtual(letter);
		} else {
			reduzirTentativasRestantes();
		}

		// Sempre mantêm status atualizados verificando depois digitar a palavra 
		atualizaStatus();
	}

	private void atualizaStatus() {
		if (descricao.equals(adivinhacaoAtual)) {
			status = status.WON;
		} else if (tentativasRestantes <= 0) {
			status = status.LOST;
		}
	}

	// Cada letras digitadas que existem na palavra
	private void atualizaAdivinhacaoAtual(String letter) {
		StringBuffer sb = new StringBuffer(adivinhacaoAtual);
		char character = letter.toCharArray()[0];
		int index = descricao.indexOf(letter);

		while (index >= 0) {
			sb.setCharAt(index, character);
			index = descricao.indexOf(character, index + 1);
		}

		adivinhacaoAtual = sb.toString();
	}

	private void reduzirTentativasRestantes() {
		tentativasRestantes--;
	}

	public boolean isPlayable() {
		return status == status.PLAYABLE;
	}

}
