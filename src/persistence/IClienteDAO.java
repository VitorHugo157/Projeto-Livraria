package persistence;

import java.util.List;

import entity.Cliente;

public interface IClienteDAO {
	
	void insertCliente(Cliente c) throws DAOException;
	void updateCliente(Cliente c) throws DAOException;
	void deleteCliente(Cliente c) throws DAOException;
	List<Cliente> selectByName(String nome) throws DAOException;
	
}
