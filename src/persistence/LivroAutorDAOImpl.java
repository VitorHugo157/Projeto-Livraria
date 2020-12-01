package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.LivroAutor;

public class LivroAutorDAOImpl implements ILivroAutorDAO {

	@Override
	public void insertLivroAutor(LivroAutor la) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO [Livro_Autor] (codigo_livro_autor, codigo_autor_livro) "
					+ "VALUES (?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, la.getCodigoLivro());
			st.setInt(2, la.getCodigoAutor());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public void updateLivroAutor(LivroAutor la) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "UPDATE [Livro_Autor] "
					+ "SET codigo_autor_livro = ? "
					+ "WHERE codigo_livro_autor = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, la.getCodigoAutor());
			st.setInt(2, la.getCodigoLivro());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public void deleteLivroAutor(LivroAutor la) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "DELETE [Livro_Autor] WHERE codigo_livro_autor = ? AND codigo_autor_livro = ? ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, la.getCodigoLivro());
			st.setInt(2, la.getCodigoAutor());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public List<LivroAutor> selectLivroAutor(LivroAutor la) throws DAOException {
		
		List<LivroAutor> lista = new ArrayList<>();
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT la.codigo_livro_autor AS codigo_livro, la.codigo_autor_livro AS codigo_autor, "
					+ "l.nome_livro AS nome_livro, l.idioma_livro AS idioma_livro, l.ano_lancamento AS ano_lancamento, "
					+ "a.nome_autor AS nome_autor, a.nacionalidade_autor AS nacionalidade_autor "
					+ "FROM [Livro] l, [Autor] a, [Livro_Autor] la "
					+ "WHERE l.codigo_livro = la.codigo_livro_autor AND a.codigo_autor = la.codigo_autor_livro";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				LivroAutor la1 = new LivroAutor();
				la1.setCodigoLivro(rs.getInt("codigo_livro"));
				la1.setCodigoAutor(rs.getInt("codigo_autor"));
				la1.setNomeLivro(rs.getString("nome_livro"));
				la1.setIdiomaLivro(rs.getString("idioma_livro"));
				la1.setAnoLancamento(rs.getDate("ano_lancamento").toLocalDate());
				la1.setNomeAutor(rs.getString("nome_autor"));
				la1.setNacionalidade(rs.getString("nacionalidade_autor"));
				lista.add(la1);
			}
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return lista;
		
	}
	
	
	
}
