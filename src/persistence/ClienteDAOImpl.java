package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Autor;
import entity.Cliente;

public class ClienteDAOImpl implements IClienteDAO {

	@Override
	public void insertCliente(Cliente c) throws DAOException {

		try {

			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO [Cliente] (cpf_cliente, nome_cliente, nascimento_cliente, logradouro_cliente, num_logradouro_cliente, complemento, telefone_cliente, email_cliente) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, c.getCpfCliente());
			st.setString(2, c.getNomeCliente());
			st.setDate(3, java.sql.Date.valueOf(c.getNascimentoCliente()));
			st.setString(4, c.getLogradouroCliente());
			st.setInt(5, c.getNumLograCliente());
			st.setString(6, c.getComplemento());
			st.setString(7, c.getTelefone());
			st.setString(8, c.getEmail());
			st.executeUpdate();
			con.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

	@Override
	public void updateCliente(Cliente c) throws DAOException {
		
		try {

			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "UPDATE [Cliente] "
					+ "SET nome_cliente = ?, nascimento_cliente = ?, logradouro_cliente = ?, num_logradouro_cliente = ?, complemento = ?, telefone_cliente = ?, email_cliente = ? "
					+ "WHERE cpf_cliente = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, c.getNomeCliente());
			st.setDate(2, java.sql.Date.valueOf(c.getNascimentoCliente()));
			st.setString(3, c.getLogradouroCliente());
			st.setInt(4, c.getNumLograCliente());
			st.setString(5, c.getComplemento());
			st.setString(6, c.getTelefone());
			st.setString(7, c.getEmail());
			st.setString(8, c.getCpfCliente());
			st.executeUpdate();
			con.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void deleteCliente(Cliente c) throws DAOException {
		
		try {

			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "DELETE [Cliente] "
					+ "WHERE cpf_cliente = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, c.getCpfCliente());
			st.executeUpdate();
			con.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<Cliente> selectByName(String nome) throws DAOException {
		
		List<Cliente> lista = new ArrayList<>();
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT SUBSTRING(cpf_cliente, 1, 3) + '.' + SUBSTRING(cpf_cliente, 4, 3) + "
			+ "'.' + SUBSTRING(cpf_cliente, 7, 3)  + '-' + SUBSTRING(cpf_cliente, 10, 2) AS cpf, "
			+ "nome_cliente, nascimento_cliente, logradouro_cliente, num_logradouro_cliente, complemento, "
			+ "CASE WHEN (LEN(telefone_cliente) = 11) THEN '(' + SUBSTRING(telefone_cliente, 1, 2) + ')' + "
			+ "SUBSTRING(telefone_cliente, 3, 5) + '-' + SUBSTRING(telefone_cliente, 8, 4) ELSE "
			+ "'(' + SUBSTRING(telefone_cliente, 1, 2) + ')' + SUBSTRING(telefone_cliente, 3, 4) + '-' + SUBSTRING(telefone_cliente, 7, 4) "
			+ "END AS tel_cliente, email_cliente "
			+ "FROM [Cliente] WHERE nome_cliente LIKE ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + nome + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Cliente c = new Cliente();
				c.setCpfCliente(rs.getString("cpf"));
				c.setNomeCliente(rs.getString("nome_cliente"));
				c.setNascimentoCliente(rs.getDate("nascimento_cliente").toLocalDate());
				c.setLogradouroCliente(rs.getString("logradouro_cliente"));
				c.setNumLograCliente(rs.getInt("num_logradouro_cliente"));
				c.setComplemento(rs.getString("complemento"));
				c.setTelefone(rs.getString("tel_cliente"));
				c.setEmail(rs.getString("email_cliente"));
				lista.add(c);
			}
			con.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return lista;
	}
	
	
}
