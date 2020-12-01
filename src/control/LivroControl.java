package control;

import java.time.LocalDate;

import entity.Livro;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.DAOException;
import persistence.ILivroDAO;
import persistence.LivroDAOImpl;

public class LivroControl {
	
	ObservableList<Livro> livros = FXCollections.observableArrayList();
	
	private IntegerProperty codigoLivro = new SimpleIntegerProperty();
	private StringProperty nomeLivro = new SimpleStringProperty();
	private StringProperty idiomaLivro = new SimpleStringProperty();
	private ObjectProperty<LocalDate> anoLancamento = new SimpleObjectProperty<>();
	
	private ILivroDAO livroDAO = new LivroDAOImpl();
	
	public Livro getLivro() {
		
		Livro l = new Livro();
		
		l.setCodigoLivro(this.codigoLivro.get());
		l.setNomeLivro(this.nomeLivro.get());
		l.setIdiomaLivro(this.idiomaLivro.get());
		l.setAnoLancamento(this.anoLancamento.get());
		
		return l;
	}
	
	public void setLivro(Livro l) {
		
		if (l != null) {
			this.codigoLivro.set(l.getCodigoLivro());
			this.nomeLivro.set(l.getNomeLivro());
			this.idiomaLivro.set(l.getIdiomaLivro());
			this.anoLancamento.set(l.getAnoLancamento());
		}
	}
	
	public void inserirLivro() throws DAOException {
		
		livroDAO.insertLivro(getLivro());
		
	}
	
	public void atualizarLivro() throws DAOException {
		
		livroDAO.updateLivro(getLivro());
		
	}
	
	public void excluirLivro() throws DAOException {
		
		livroDAO.deleteLivro(getLivro());
		
	}
	
	public void buscarLivro() throws DAOException {
		
		int codigo = this.codigoLivro.get();
		livros.clear();
		livros.addAll(livroDAO.selectByCodigo(codigo));
		
	}
	
	public ObservableList<Livro> getLista() {
		return livros;
	}
	
	public IntegerProperty getCodigoLivroProperty() {
		return codigoLivro;
	}

	public StringProperty getNomeLivroProperty() {
		return nomeLivro;
	}

	public StringProperty getIdiomaLivroProperty() {
		return idiomaLivro;
	}

	public ObjectProperty<LocalDate> getAnoLancamentoProperty() {
		return anoLancamento;
	}
}
