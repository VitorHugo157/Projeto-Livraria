package persistence;

import java.util.List;

import entity.LivroAutor;

public interface ILivroAutorDAO {
	
	void insertLivroAutor(LivroAutor la) throws DAOException;
	void updateLivroAutor(LivroAutor la) throws DAOException;
	void deleteLivroAutor(LivroAutor la) throws DAOException;
	List<LivroAutor> selectLivroAutor(LivroAutor la) throws DAOException;
	
}
