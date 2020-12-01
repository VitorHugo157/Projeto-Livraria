package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.ClienteLivro;

public class ClienteLivroDAOImpl implements IClienteLivroDAO {

	@Override
	public void insertClienteLivro(ClienteLivro cl) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO [Cliente_Livro] (cpf_cliente_livro, codigo_livro_cliente) "
					+ "VALUES (?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, cl.getCpfCliente());
			st.setInt(2, cl.getCodigoLivro());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public void updateClienteLivro(ClienteLivro cl) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "UPDATE [Cliente_Livro] "
					+ "SET codigo_livro_cliente = ? "
					+ "WHERE cpf_cliente_livro = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, cl.getCodigoLivro());
			st.setString(2, cl.getCpfCliente());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public void deleteClienteLivro(ClienteLivro cl) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "DELETE [Cliente_Livro] WHERE cpf_cliente_livro = ? AND codigo_livro_cliente = ? ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, cl.getCpfCliente());
			st.setInt(2, cl.getCodigoLivro());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public List<ClienteLivro> selectClienteLivro(ClienteLivro cl) throws DAOException {
		
		List<ClienteLivro> lista = new ArrayList<>();
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT SUBSTRING(cpf_cliente, 1, 3) + '.' + SUBSTRING(cpf_cliente, 4, 3) + ");
			sql.append("'.' + SUBSTRING(cpf_cliente, 7, 3)  + '-' + SUBSTRING(cpf_cliente, 10, 2) AS cpf, cl.codigo_livro_cliente AS codigo_livro, ");
			sql.append("c.nome_cliente AS nome_cliente, ");
			sql.append("l.nome_livro AS nome_livro, l.idioma_livro AS idioma_livro, l.ano_lancamento AS ano_lancamento ");
			sql.append("FROM [Cliente] c, [Livro] l, [Cliente_Livro] cl ");
			sql.append("WHERE c.cpf_cliente = cl.cpf_cliente_livro AND ");
			sql.append("l.codigo_livro = cl.codigo_livro_cliente");
			PreparedStatement st = con.prepareStatement(sql.toString());
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				ClienteLivro cl1 = new ClienteLivro();
				cl1.setCpfCliente(rs.getString("cpf"));
				cl1.setCodigoLivro(rs.getInt("codigo_livro"));
				cl1.setNomeCliente(rs.getString("nome_cliente"));
				cl1.setNomeLivro(rs.getString("nome_livro"));
				cl1.setIdiomaLivro(rs.getString("idioma_livro"));
				cl1.setAnoLancamento(rs.getDate("ano_lancamento").toLocalDate());
				lista.add(cl1);
			}
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return lista;
		
	}
	
	
	
}
