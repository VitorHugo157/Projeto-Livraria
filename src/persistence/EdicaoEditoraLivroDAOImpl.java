package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.EdicaoEditoraLivro;

public class EdicaoEditoraLivroDAOImpl implements IEdicaoEditoraLivroDAO {

	@Override
	public void insertEdicaoEditoraLivro(EdicaoEditoraLivro eel) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO [Edicao_Editora_Livro] (isbn_edicao, codigo_editora, codigo_livro) "
					+ "VALUES (?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, eel.getIsbnEdicao());
			st.setInt(2, eel.getCodigoEditora());
			st.setInt(3, eel.getCodigoLivro());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public void updateEdicaoEditoraLivro(EdicaoEditoraLivro eel) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "UPDATE [Edicao_Editora_Livro] "
					+ "SET codigo_editora = ?, codigo_livro = ? "
					+ "WHERE isbn_edicao = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, eel.getCodigoEditora());
			st.setInt(2, eel.getCodigoLivro());
			st.setInt(3, eel.getIsbnEdicao());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public void deleteEdicaoEditoraLivro(EdicaoEditoraLivro eel) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "DELETE [Edicao_Editora_Livro] "
					+ "WHERE isbn_edicao = ? AND codigo_editora = ? AND codigo_livro = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, eel.getIsbnEdicao());
			st.setInt(2, eel.getCodigoEditora());
			st.setInt(3, eel.getCodigoLivro());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public List<EdicaoEditoraLivro> selectEdicaoEditoraLivro(EdicaoEditoraLivro eel) throws DAOException {
		
		List<EdicaoEditoraLivro> lista = new ArrayList<>();
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
					sql.append("SELECT eel.isbn_edicao AS isbn, eel.codigo_editora AS codigo_editora, eel.codigo_livro AS codigo_livro, ");
					sql.append("edicao.preco_edicao AS preco, edicao.ano_edicao AS ano_edicao, ");
					sql.append("editora.nome_editora AS nome_editora, ");
					sql.append("CASE WHEN (LEN(editora.telefone_editora) = 11) THEN '(' + SUBSTRING(editora.telefone_editora, 1, 2) + ')' + ");
					sql.append("SUBSTRING(editora.telefone_editora, 3, 5) + '-' + SUBSTRING(editora.telefone_editora, 8, 4) ELSE ");
					sql.append("'(' + SUBSTRING(editora.telefone_editora, 1, 2) + ')' + SUBSTRING(editora.telefone_editora, 3, 4) + '-' + SUBSTRING(editora.telefone_editora, 7, 4) ");
					sql.append("END AS tel_editora, ");
					sql.append("l.nome_livro AS nome_livro, l.idioma_livro AS idioma_livro, l.ano_lancamento ");
					sql.append("FROM [Edicao] edicao, [Editora] editora, [Livro] l, [Edicao_Editora_Livro] eel ");
					sql.append("WHERE edicao.isbn = eel.isbn_edicao AND ");
					sql.append("editora.codigo_editora = eel.codigo_editora AND ");
					sql.append("l.codigo_livro = eel.codigo_livro");
			PreparedStatement st = con.prepareStatement(sql.toString());
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				EdicaoEditoraLivro eel1 = new EdicaoEditoraLivro();
				eel1.setIsbnEdicao(rs.getInt("isbn"));
				eel1.setCodigoEditora(rs.getInt("codigo_editora"));
				eel1.setCodigoLivro(rs.getInt("codigo_livro"));
				eel1.setPrecoEdicao(rs.getDouble("preco"));
				eel1.setAnoEdicao(rs.getDate("ano_edicao").toLocalDate());
				eel1.setNomeEditora(rs.getString("nome_editora"));
				eel1.setTelefoneEditora(rs.getString("tel_editora"));
				eel1.setNomeLivro(rs.getString("nome_livro"));
				eel1.setIdiomaLivro(rs.getString("idioma_livro"));
				eel1.setAnoLancamento(rs.getDate("ano_lancamento").toLocalDate());
				lista.add(eel1);
			}
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return lista;
		
	}
}
