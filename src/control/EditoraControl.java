package control;

import entity.Editora;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.DAOException;
import persistence.EditoraDAOImpl;
import persistence.IEditoraDAO;

public class EditoraControl {
	
	ObservableList<Editora> editoras = FXCollections.observableArrayList();
	
	private IntegerProperty codigoEditora = new SimpleIntegerProperty();
	private StringProperty nomeEditora = new SimpleStringProperty();
	private StringProperty logradouroEditora = new SimpleStringProperty();
	private IntegerProperty numLograEditora = new SimpleIntegerProperty();
	private StringProperty cepEditora = new SimpleStringProperty();
	private StringProperty telefoneEditora = new SimpleStringProperty();
	
	private IEditoraDAO editoraDAO = new EditoraDAOImpl();
	
	public Editora getEditora() {
		Editora e = new Editora();
		
		e.setCodigoEditora(this.codigoEditora.get());
		e.setNomeEditora(this.nomeEditora.get());
		e.setLogradouroEditora(this.logradouroEditora.get());
		e.setNumLograEditora(this.numLograEditora.get());
		e.setCepEditora(this.cepEditora.get());
		e.setTelefoneEditora(this.telefoneEditora.get());
		
		return e;
	}
	
	public void setEditora(Editora e) {
		if (e != null) {
			this.codigoEditora.set(e.getCodigoEditora());
			this.nomeEditora.set(e.getNomeEditora());
			this.logradouroEditora.set(e.getLogradouroEditora());
			this.numLograEditora.set(e.getNumLograEditora());
			this.cepEditora.set(e.getCepEditora());
			this.telefoneEditora.set(e.getTelefoneEditora());
		}
	}
	
	public void inserirEditora() throws DAOException {

		editoraDAO.insertEditora(getEditora());
	
	}
	
	public void atualizarEditora() throws DAOException {
		
		editoraDAO.updateEditora(getEditora());
		
	}
	
	public void excluirEditora() throws DAOException {
		
		editoraDAO.deleteEditora(getEditora());
		
	}
	
	public void buscarEditora() throws DAOException {

		String nome = this.nomeEditora.get();
		editoras.clear();
		editoras.addAll(editoraDAO.selectByName(nome));
		
	}

	public ObservableList<Editora> getEditoras() {
		return editoras;
	}

	public IntegerProperty getCodigoEditoraProperty() {
		return codigoEditora;
	}

	public StringProperty getNomeEditoraProperty() {
		return nomeEditora;
	}

	public StringProperty getLogradouroEditoraProperty() {
		return logradouroEditora;
	}

	public IntegerProperty getNumLograEditoraProperty() {
		return numLograEditora;
	}

	public StringProperty getCepEditoraProperty() {
		return cepEditora;
	}

	public StringProperty getTelefoneEditoraProperty() {
		return telefoneEditora;
	}
}
