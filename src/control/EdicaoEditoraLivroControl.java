package control;

import entity.EdicaoEditoraLivro;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.DAOException;
import persistence.EdicaoEditoraLivroDAOImpl;
import persistence.IEdicaoEditoraLivroDAO;

public class EdicaoEditoraLivroControl {
	
	private ObservableList<EdicaoEditoraLivro> lista = FXCollections.observableArrayList();
	
	private IntegerProperty isbnEdicao = new SimpleIntegerProperty();
	private IntegerProperty codigoEditora = new SimpleIntegerProperty();
	private IntegerProperty codigoLivro = new SimpleIntegerProperty();
	
	private IEdicaoEditoraLivroDAO eelDAO = new EdicaoEditoraLivroDAOImpl();
	
	public EdicaoEditoraLivro getEdicaoEditoraLivro() {
		
		EdicaoEditoraLivro eel = new EdicaoEditoraLivro();
		eel.setIsbnEdicao(this.isbnEdicao.get());
		eel.setCodigoEditora(this.codigoEditora.get());
		eel.setCodigoLivro(this.codigoLivro.get());
		
		return eel;
	}
	
	public void setEdicaoEditoraLivro(EdicaoEditoraLivro eel) {
		
		if (eel != null) {
			this.isbnEdicao.set(eel.getIsbnEdicao());
			this.codigoEditora.set(eel.getCodigoEditora());
			this.codigoLivro.set(eel.getCodigoLivro());
		}
	}
	
	public void inserirEdicaoEditoraLivro() throws DAOException {
		
		eelDAO.insertEdicaoEditoraLivro(getEdicaoEditoraLivro());
		
	}
	
	public void atualizarEdicaoEditoraLivro() throws DAOException {
		
		eelDAO.updateEdicaoEditoraLivro(getEdicaoEditoraLivro());
		
	}
	
	public void excluirEdicaoEditoraLivro() throws DAOException {
		
		eelDAO.deleteEdicaoEditoraLivro(getEdicaoEditoraLivro());
		
	}
	
	public void buscarEdicaoEditoraLivro() throws DAOException {
		
		lista.clear();
		lista.addAll(eelDAO.selectEdicaoEditoraLivro(getEdicaoEditoraLivro()));
		
	}
	
	public ObservableList<EdicaoEditoraLivro> getLista() {
		return lista;
	}
	public IntegerProperty getIsbnEdicaoProperty() {
		return isbnEdicao;
	}
	public IntegerProperty getCodigoEditoraProperty() {
		return codigoEditora;
	}
	public IntegerProperty getCodigoLivroProperty() {
		return codigoLivro;
	}
}
