package pt.jogodaforca.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe implementa entidade
 * @author Walyson
 * @version 1.0
 * @since   2019-12-05
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="dificuldades")
public class Dificuldade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;

	@Override
	public String toString() {
		return "" + descricao + "";
	}

}
