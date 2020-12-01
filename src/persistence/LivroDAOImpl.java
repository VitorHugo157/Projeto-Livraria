package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Autor;
import entity.Livro;

public class LivroDAOImpl implements ILivroDAO {

	@Override
	public void insertLivro(Livro l) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO [Livro] (codigo_livro, nome_livro, idioma_livro, ano_lancamento) "
					+ "VALUES (?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, l.getCodigoLivro());
			st.setString(2, l.getNomeLivro());
			st.setString(3, l.getIdiomaLivro());
			st.setDate(4, java.sql.Date.valueOf(l.getAnoLancamento()));
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public void updateLivro(Livro l) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "UPDATE [Livro] "
					+ "SET nome_livro = ?, idioma_livro = ?, ano_lancamento = ? "
					+ "WHERE codigo_livro = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, l.getNomeLivro());
			st.setString(2, l.getIdiomaLivro());
			st.setDate(3, java.sql.Date.valueOf(l.getAnoLancamento()));
			st.setInt(4, l.getCodigoLivro());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public void deleteLivro(Livro l) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "DELETE [Livro] WHERE codigo_livro = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, l.getCodigoLivro());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public List<Livro> selectByCodigo(int codigo) throws DAOException {
		
		List<Livro> lista = new ArrayList<>();
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM [Livro] WHERE codigo_livro = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, codigo);
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Livro l = new Livro();
				l.setCodigoLivro(rs.getInt("codigo_livro"));
				l.setNomeLivro(rs.getString("nome_livro"));
				l.setIdiomaLivro(rs.getString("idioma_livro"));
				l.setAnoLancamento(rs.getDate("ano_lancamento").toLocalDate());
				lista.add(l);
			}
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return lista;
		
	}
	
	
	
}
