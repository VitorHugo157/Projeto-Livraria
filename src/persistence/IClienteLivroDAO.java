package persistence;

import java.util.List;

import entity.ClienteLivro;

public interface IClienteLivroDAO {
	
	void insertClienteLivro(ClienteLivro cl) throws DAOException;
	void updateClienteLivro(ClienteLivro cl) throws DAOException;
	void deleteClienteLivro(ClienteLivro cl) throws DAOException;
	List<ClienteLivro> selectClienteLivro(ClienteLivro cl) throws DAOException;
	
}
