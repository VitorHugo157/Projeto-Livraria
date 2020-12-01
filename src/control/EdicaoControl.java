package control;

import java.time.LocalDate;

import entity.Edicao;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.DAOException;
import persistence.EdicaoDAOImpl;
import persistence.IEdicaoDAO;

public class EdicaoControl {
	
	ObservableList<Edicao> edicoes = FXCollections.observableArrayList();
	
	private IntegerProperty isbn = new SimpleIntegerProperty();
	private DoubleProperty precoEdicao = new SimpleDoubleProperty();
	private ObjectProperty<LocalDate> anoEdicao = new SimpleObjectProperty<>();
	private IntegerProperty numPaginas = new SimpleIntegerProperty();
	private IntegerProperty qtdEstoque = new SimpleIntegerProperty();
	
	private IEdicaoDAO edicaoDAO = new EdicaoDAOImpl();
	
	public Edicao getEdicao() {
		Edicao e = new Edicao();
		
		e.setIsbn(this.isbn.get());
		e.setPrecoEdicao(this.precoEdicao.get());
		e.setAnoEdicao(this.anoEdicao.get());
		e.setNumPaginas(this.numPaginas.get());
		e.setQtdEstoque(this.qtdEstoque.get());
		
		return e;
	}
	
	public void setEdicao(Edicao e) {
		if (e != null) {
			this.isbn.set(e.getIsbn());
			this.precoEdicao.set(e.getPrecoEdicao());
			this.anoEdicao.set(e.getAnoEdicao());
			this.numPaginas.set(e.getNumPaginas());
			this.qtdEstoque.set(e.getQtdEstoque());
		}
	}
	
	public void inserirEdicao() throws DAOException {
		
		edicaoDAO.insertEdicao(getEdicao());
		
	}
	
	public void atualizarEdicao() throws DAOException {
		
		edicaoDAO.updateEdicao(getEdicao());
		
	}
	
	public void excluirEdicao() throws DAOException {
		
		edicaoDAO.deleteEdicao(getEdicao());
		
	}
	
	public void buscarEdicoes() throws DAOException {
		
		int isbnSelect = this.isbn.get();
		edicoes.clear();
		edicoes.addAll(edicaoDAO.selectByIsbn(isbnSelect));
		
	}

	public ObservableList<Edicao> getEdicoes() {
		return edicoes;
	}
	public IntegerProperty getIsbnProperty() {
		return isbn;
	}
	public DoubleProperty getPrecoEdicaoProperty() {
		return precoEdicao;
	}
	public ObjectProperty<LocalDate> getAnoEdicaoProperty() {
		return anoEdicao;
	}
	public IntegerProperty getNumPaginasProperty() {
		return numPaginas;
	}
	public IntegerProperty getQtdEstoqueProperty() {
		return qtdEstoque;
	}
}
