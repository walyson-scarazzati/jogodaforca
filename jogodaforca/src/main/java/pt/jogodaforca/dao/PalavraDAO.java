package pt.jogodaforca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.jogodaforca.conf.Connect;
import pt.jogodaforca.model.Palavra;

/**
 * Classe implementa banco de dados
 * 
 * @author Walyson
 * @version 1.0
 * @since 2019-12-05
 */
@Repository
@Transactional
public class PalavraDAO {

	/**
	 * Lista todas as palavras fazendo inner join com dificuldade e categoria
	 * escolhidos anteriormente
	 * 
	 * @param dificuldade_id
	 * @param categoria_id
	 * @return palavras
	 */

	public static List<Palavra> listar(Long dificuldade_id, Long categoria_id) {
		Connect obj_DB_Connection = new Connect();
		Connection connection = obj_DB_Connection.get_connection();
		PreparedStatement ps = null;
		List<Palavra> palavras = new ArrayList<Palavra>();
		try {
			Palavra palavra = null;
			String query = "select * from palavra AS p INNER JOIN dificuldade AS d ON p.dificuldade_id = d.id INNER JOIN palavra_categoria AS pc ON p.id = pc.palavra_id INNER JOIN categoria c ON c.id = pc.categoria_id where"
					+ " d.id =" + dificuldade_id + " and c.id=" + categoria_id;
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				palavra = new Palavra();
				palavra.setId(rs.getLong(1));
				palavra.setDescricao(rs.getString(2));

				palavras.add(palavra);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return palavras;

	}

}
