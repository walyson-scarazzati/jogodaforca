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
import pt.jogodaforca.model.Dificuldade;

/**
 * Classe implementa banco de dados
 * 
 * @author Walyson
 * @version 1.0
 * @since 2019-12-05
 */
@Repository
@Transactional
public class DificuldadeDAO {

	/**
	 * Lista todas níveis de dificuldades das palavras
	 * 
	 * @return dificuldades
	 */

	public List<Dificuldade> listar() {
		Connect obj_DB_Connection = new Connect();
		Connection connection = obj_DB_Connection.get_connection();
		PreparedStatement ps = null;
		List<Dificuldade> dificuldades = new ArrayList<Dificuldade>();
		try {
			Dificuldade dificuldade = null;
			String query = "select * from dificuldades";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dificuldade = new Dificuldade();
				dificuldade.setId(rs.getLong(1));
				dificuldade.setDescricao(rs.getString(2));
				dificuldades.add(dificuldade);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return dificuldades;
	}

}
