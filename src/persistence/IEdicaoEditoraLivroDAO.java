package persistence;

import java.util.List;

import entity.EdicaoEditoraLivro;

public interface IEdicaoEditoraLivroDAO {
	
	void insertEdicaoEditoraLivro(EdicaoEditoraLivro eel) throws DAOException;
	void updateEdicaoEditoraLivro(EdicaoEditoraLivro eel) throws DAOException;
	void deleteEdicaoEditoraLivro(EdicaoEditoraLivro eel) throws DAOException;
	List<EdicaoEditoraLivro> selectEdicaoEditoraLivro(EdicaoEditoraLivro eel) throws DAOException;
	
}
