package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Edicao;

public class EdicaoDAOImpl implements IEdicaoDAO {

	@Override
	public void insertEdicao(Edicao e) throws DAOException {
		
		try {

			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO [Edicao] (isbn, preco_edicao, ano_edicao, num_paginas_edicao, qtd_estoque) "
					+ "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, e.getIsbn());
			st.setDouble(2, e.getPrecoEdicao());
			st.setDate(3, java.sql.Date.valueOf(e.getAnoEdicao()));
			st.setInt(4, e.getNumPaginas());
			st.setInt(5, e.getQtdEstoque());
			st.executeUpdate();
			con.close();

		} catch (SQLException e1) {
			throw new DAOException(e1);
		}
		
	}

	@Override
	public void updateEdicao(Edicao e) throws DAOException {
		
		try {

			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "UPDATE [Edicao] "
					+ "SET preco_edicao = ?, ano_edicao = ?, num_paginas_edicao = ?, qtd_estoque = ? "
					+ "WHERE isbn = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setDouble(1, e.getPrecoEdicao());
			st.setDate(2, java.sql.Date.valueOf(e.getAnoEdicao()));
			st.setInt(3, e.getNumPaginas());
			st.setInt(4, e.getQtdEstoque());
			st.setInt(5, e.getIsbn());
			st.executeUpdate();
			con.close();

		} catch (SQLException e1) {
			throw new DAOException(e1);
		}
		
	}

	@Override
	public void deleteEdicao(Edicao e) throws DAOException {
		
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "DELETE [Edicao] WHERE isbn = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, e.getIsbn());
			st.executeUpdate();
			con.close();
			
		} catch (SQLException e1) {
			throw new DAOException(e1);
		}
		
	}

	@Override
	public List<Edicao> selectByIsbn(int isbn) throws DAOException {
		
		List<Edicao> lista = new ArrayList<>();
		try {
			
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM [Edicao] WHERE isbn = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, isbn);
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Edicao e = new Edicao();
				e.setIsbn(rs.getInt("isbn"));
				e.setPrecoEdicao(rs.getDouble("preco_edicao"));
				e.setAnoEdicao(rs.getDate("ano_edicao").toLocalDate());
				e.setNumPaginas(rs.getInt("num_paginas_edicao"));
				e.setQtdEstoque(rs.getInt("qtd_estoque"));
				lista.add(e);
			}
			con.close();
			
		} catch (SQLException e1) {
			throw new DAOException(e1);
		}
		return lista;
	}
	
	
	
}
