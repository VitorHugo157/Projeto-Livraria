package control;

import entity.ClienteLivro;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.ClienteLivroDAOImpl;
import persistence.DAOException;
import persistence.IClienteLivroDAO;

public class ClienteLivroControl {
	
	ObservableList<ClienteLivro> lista = FXCollections.observableArrayList();
	
	private StringProperty cpfCliente = new SimpleStringProperty();
	private IntegerProperty codigoLivro = new SimpleIntegerProperty();
	
	private IClienteLivroDAO clienteLivroDAO = new ClienteLivroDAOImpl();
	
	public ClienteLivro getClienteLivro() {
		
		ClienteLivro cl = new ClienteLivro();
		cl.setCpfCliente(this.cpfCliente.get());
		cl.setCodigoLivro(this.codigoLivro.get());
		
		return cl;
	}
	
	public void setClienteLivro(ClienteLivro cl) {
		
		if (cl != null) {
			this.cpfCliente.set(cl.getCpfCliente());
			this.codigoLivro.set(cl.getCodigoLivro());
		}
	}
	
	public void inserirClienteLivro() throws DAOException {
		
		clienteLivroDAO.insertClienteLivro(getClienteLivro());
	}
	
	public void atualizarClienteLivro() throws DAOException {
		
		clienteLivroDAO.updateClienteLivro(getClienteLivro());
		
	}
	
	public void excluirClienteLivro() throws DAOException {
	
		clienteLivroDAO.deleteClienteLivro(getClienteLivro());
		
	}
	
	public void buscarClienteLivro() throws DAOException {
		
		lista.clear();
		lista.addAll(clienteLivroDAO.selectClienteLivro(getClienteLivro()));
		
	}
	
	public ObservableList<ClienteLivro> getLista() {
		return lista;
	}
	public StringProperty getCpfClienteProperty() {
		return cpfCliente;
	}
	public IntegerProperty getCodigoLivroProperty() {
		return codigoLivro;
	}	
}
