package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Autor;

public class AutorDAOImpl implements IAutorDAO {

	@Override
	public void insertAutor(Autor a) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO [Autor] (codigo_autor, nome_autor, nascimento_autor, nacionalidade_autor, biografia_autor) "
					+ "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, a.getCodigoAutor());
			st.setString(2, a.getNomeAutor());
			st.setDate(3, java.sql.Date.valueOf(a.getNascimentoAutor()));
			st.setString(4, a.getNacionalidadeAutor());
			st.setString(5, a.getBiografiaAutor());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public void updateAutor(Autor a) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "UPDATE [Autor] "
					+ "SET nome_autor = ?, nascimento_autor = ?, nacionalidade_autor = ?, biografia_autor = ? "
					+ "WHERE codigo_autor = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, a.getNomeAutor());
			st.setDate(2, java.sql.Date.valueOf(a.getNascimentoAutor()));
			st.setString(3, a.getNacionalidadeAutor());
			st.setString(4, a.getBiografiaAutor());
			st.setInt(5, a.getCodigoAutor());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public void deleteAutor(Autor a) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "DELETE [Autor] WHERE codigo_autor = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, a.getCodigoAutor());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public List<Autor> selectByNameAutor(String nome) throws DAOException {
		
		List<Autor> lista = new ArrayList<>();
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM [Autor] WHERE nome_autor LIKE ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + nome + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Autor a = new Autor();
				a.setCodigoAutor(rs.getInt("codigo_autor"));
				a.setNomeAutor(rs.getString("nome_autor"));
				a.setNascimentoAutor(rs.getDate("nascimento_autor").toLocalDate());
				a.setNacionalidadeAutor(rs.getString("nacionalidade_autor"));
				a.setBiografiaAutor(rs.getString("biografia_autor"));
				lista.add(a);
			}
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return lista;
		
	}
}
