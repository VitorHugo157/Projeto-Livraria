package persistence;

import java.util.List;

import entity.Editora;

public interface IEditoraDAO {
	
	void insertEditora(Editora e) throws DAOException;
	void updateEditora(Editora e) throws DAOException;
	void deleteEditora(Editora e) throws DAOException;
	List<Editora> selectByName(String nome) throws DAOException;
	
}
