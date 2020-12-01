package control;

import java.time.LocalDate;

import entity.Autor;
import entity.Livro;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.AutorDAOImpl;
import persistence.DAOException;
import persistence.IAutorDAO;

public class AutorControl {
	
	ObservableList<Autor> autores = FXCollections.observableArrayList();
	
	private IntegerProperty codigoAutor = new SimpleIntegerProperty();
	private StringProperty nomeAutor = new SimpleStringProperty();
	private ObjectProperty<LocalDate> nascimentoAutor = new SimpleObjectProperty<>();
	private StringProperty nacionalidadeAutor = new SimpleStringProperty();
	private StringProperty biografiaAutor = new SimpleStringProperty();
	
	private IAutorDAO autorDAO = new AutorDAOImpl();
	
	public Autor getAutor() {
		
		Autor a = new Autor();
		a.setCodigoAutor(this.codigoAutor.get());
		a.setNomeAutor(this.nomeAutor.get());
		a.setNascimentoAutor(this.nascimentoAutor.get());
		a.setNacionalidadeAutor(this.nacionalidadeAutor.get());
		a.setBiografiaAutor(this.biografiaAutor.get());
		
		return a;
	}
	
	public void setAutor(Autor a) {
		
		if (a != null) {
			this.codigoAutor.set(a.getCodigoAutor());
			this.nomeAutor.set(a.getNomeAutor());
			this.nascimentoAutor.set(a.getNascimentoAutor());
			this.nacionalidadeAutor.set(a.getNacionalidadeAutor());
			this.biografiaAutor.set(a.getBiografiaAutor());
		}
	}
	
	public void inserirAutor() throws DAOException {
		
		autorDAO.insertAutor(getAutor());
		
	}
	
	public void atualizarAutor() throws DAOException {
		
		autorDAO.updateAutor(getAutor());
		
	}
	
	public void excluirAutor() throws DAOException {

		autorDAO.deleteAutor(getAutor());

	}
	
	public void buscarAutor() throws DAOException {
		
		String nome = this.nomeAutor.get();
		autores.clear();
		autores.addAll(autorDAO.selectByNameAutor(nome));
		
	}
	
	public ObservableList<Autor> getLista() {
		return autores;
	}
	
	public IntegerProperty getCodigoAutorProperty() {
		return codigoAutor;
	}

	public StringProperty getNomeAutorProperty() {
		return nomeAutor;
	}

	public ObjectProperty<LocalDate> getNascimentoAutorProperty() {
		return nascimentoAutor;
	}

	public StringProperty getNacionalidadeAutorProperty() {
		return nacionalidadeAutor;
	}

	public StringProperty getBiografiaAutorProperty() {
		return biografiaAutor;
	}
}
