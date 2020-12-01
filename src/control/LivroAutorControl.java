package control;

import entity.LivroAutor;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.DAOException;
import persistence.ILivroAutorDAO;
import persistence.LivroAutorDAOImpl;

public class LivroAutorControl {
	
	private ObservableList<LivroAutor> lista = FXCollections.observableArrayList();
	
	private IntegerProperty codigoLivro = new SimpleIntegerProperty();
	private IntegerProperty codigoAutor = new SimpleIntegerProperty();
	
	private ILivroAutorDAO livroAutorDAO = new LivroAutorDAOImpl();
	
	public LivroAutor getLivroAutor() {
		
		LivroAutor la = new LivroAutor();
		la.setCodigoLivro(this.codigoLivro.get());
		la.setCodigoAutor(this.codigoAutor.get());
		
		return la;
	}
	
	public void setLivroAutor(LivroAutor la) {
		
		if (la != null) {
			this.codigoLivro.set(la.getCodigoLivro());
			this.codigoAutor.set(la.getCodigoAutor());
		}
	}
	
	public void inserirLivroAutor() throws DAOException {
		
		livroAutorDAO.insertLivroAutor(getLivroAutor());
		
	}
	
	public void atualizarLivroAutor() throws DAOException {
		
		livroAutorDAO.updateLivroAutor(getLivroAutor());
		
	}
	
	public void excluirLivroAutor() throws DAOException {
		
		livroAutorDAO.deleteLivroAutor(getLivroAutor());
		
	}
	
	public void buscarLivroAutor() throws DAOException {
		
		lista.clear();
		lista.addAll(livroAutorDAO.selectLivroAutor(getLivroAutor()));
		
	}
	
	public ObservableList<LivroAutor> getLista() {
		return lista;
	}
	
	public IntegerProperty getCodigoLivroProperty() {
		return codigoLivro;
	}
	public IntegerProperty getCodigoAutorProperty() {
		return codigoAutor;
	}
}
