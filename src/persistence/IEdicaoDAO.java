package persistence;

import java.util.List;

import entity.Edicao;

public interface IEdicaoDAO {

	void insertEdicao(Edicao e) throws DAOException;
	void updateEdicao(Edicao e) throws DAOException;
	void deleteEdicao(Edicao e) throws DAOException;
	List<Edicao> selectByIsbn(int isbn) throws DAOException;
	
}
