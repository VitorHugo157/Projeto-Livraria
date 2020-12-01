package persistence;

import java.util.List;

import entity.Livro;

public interface ILivroDAO {
	
	void insertLivro(Livro l) throws DAOException;
	void updateLivro(Livro l) throws DAOException;
	void deleteLivro(Livro l) throws DAOException;
	List<Livro> selectByCodigo(int codigo) throws DAOException;
	
}
