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
import pt.jogodaforca.model.Categoria;

/**
 * Classe implementa banco de dados
 * 
 * @author Walyson
 * @version 1.0
 * @since 2019-12-05
 */
@Repository
@Transactional
public class CategoriaDAO {

	/**
	 * Lista todas categorias de palavras
	 * 
	 * @return categorias
	 */
	
	public List<Categoria> listar() {
		Connect obj_DB_Connection = new Connect();
		Connection connection = obj_DB_Connection.get_connection();
		PreparedStatement ps = null;
		List<Categoria> categorias = new ArrayList<Categoria>();
		try {
			Categoria categoria = null;
			String query = "select * from categorias";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				categoria = new Categoria();
				categoria.setId(rs.getLong(1));
				categoria.setDescricao(rs.getString(2));
				categorias.add(categoria);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return categorias;
	}
		 

}
