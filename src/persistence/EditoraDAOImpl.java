package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Edicao;
import entity.Editora;

public class EditoraDAOImpl implements IEditoraDAO {

	@Override
	public void insertEditora(Editora e) throws DAOException {
		
		try {

			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO [Editora] (codigo_editora, nome_editora, logradouro_editora, num_logradouro_editora, cep_editora, telefone_editora) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, e.getCodigoEditora());
			st.setString(2, e.getNomeEditora());
			st.setString(3, e.getLogradouroEditora());
			st.setInt(4, e.getNumLograEditora());
			st.setString(5, e.getCepEditora());
			st.setString(6, e.getTelefoneEditora());
			st.executeUpdate();
			con.close();

		} catch (SQLException e1) {
			throw new DAOException(e1);
		}
		
	}

	@Override
	public void updateEditora(Editora e) throws DAOException {
		
		try {

			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "UPDATE [Editora] "
					+ "SET nome_editora = ?, logradouro_editora = ?, num_logradouro_editora = ?, cep_editora = ?, telefone_editora = ? "
					+ "WHERE codigo_editora = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, e.getNomeEditora());
			st.setString(2, e.getLogradouroEditora());
			st.setInt(3, e.getNumLograEditora());
			st.setString(4, e.getCepEditora());
			st.setString(5, e.getTelefoneEditora());
			st.setInt(6, e.getCodigoEditora());
			st.executeUpdate();
			con.close();

		} catch (SQLException e1) {
			throw new DAOException(e1);
		}
		
	}

	@Override
	public void deleteEditora(Editora e) throws DAOException {
		
		try {

			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "DELETE [Editora] WHERE codigo_editora = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, e.getCodigoEditora());
			st.executeUpdate();
			con.close();

		} catch (SQLException e1) {
			throw new DAOException(e1);
		}
		
	}

	@Override
	public List<Editora> selectByName(String nome) throws DAOException {
		
		List<Editora> lista = new ArrayList<>();
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM [Editora] WHERE nome_editora LIKE ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + nome + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Editora e = new Editora();
				e.setCodigoEditora(rs.getInt("codigo_editora"));
				e.setNomeEditora(rs.getString("nome_editora"));
				e.setLogradouroEditora(rs.getString("logradouro_editora"));
				e.setNumLograEditora(rs.getInt("num_logradouro_editora"));
				e.setCepEditora(rs.getString("cep_editora"));
				e.setTelefoneEditora(rs.getString("telefone_editora"));
				lista.add(e);
			}
			con.close();
			
		} catch (SQLException e1) {
			throw new DAOException(e1);
		}
		return lista;
		
	}
	
	
	
}
