package persistence;

import java.util.List;

import entity.Autor;

public interface IAutorDAO {
	
	void insertAutor(Autor a) throws DAOException;
	void updateAutor(Autor a) throws DAOException;
	void deleteAutor(Autor a) throws DAOException;
	List<Autor> selectByNameAutor(String nome) throws DAOException;
	
}
