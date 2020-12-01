package control;

import java.time.LocalDate;

import entity.Cliente;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.ClienteDAOImpl;
import persistence.DAOException;
import persistence.IClienteDAO;

public class ClienteControl {
	
	ObservableList<Cliente> clientes = FXCollections.observableArrayList();
	
	private StringProperty cpfCliente = new SimpleStringProperty();
	private StringProperty nomeCliente = new SimpleStringProperty();
	private ObjectProperty<LocalDate> nascimentoCliente = new SimpleObjectProperty<>();
	private StringProperty logradouroCliente = new SimpleStringProperty();
	private IntegerProperty numLograCliente = new SimpleIntegerProperty();
	private StringProperty complemento = new SimpleStringProperty();
	private StringProperty telefoneCliente = new  SimpleStringProperty();
	private StringProperty emailCliente = new SimpleStringProperty();
	
	private IClienteDAO clienteDAO = new ClienteDAOImpl();
	
	public Cliente getCliente() {
		Cliente c = new Cliente();
		c.setCpfCliente(this.cpfCliente.get());
		c.setNomeCliente(this.nomeCliente.get());
		c.setNascimentoCliente(this.nascimentoCliente.get());
		c.setLogradouroCliente(this.logradouroCliente.get());
		c.setNumLograCliente(this.numLograCliente.get());
		c.setComplemento(this.complemento.get());
		c.setTelefone(this.telefoneCliente.get());
		c.setEmail(this.emailCliente.get());
		
		return c;
	}
	
	public void setCliente(Cliente c) {
		if (c != null) {
			this.cpfCliente.set(c.getCpfCliente());
			this.nomeCliente.set(c.getNomeCliente());
			this.nascimentoCliente.set(c.getNascimentoCliente());
			this.logradouroCliente.set(c.getLogradouroCliente());
			this.numLograCliente.set(c.getNumLograCliente());
			this.complemento.set(c.getComplemento());
			this.telefoneCliente.set(c.getTelefone());
			this.emailCliente.set(c.getEmail());
		}
	}
	
	public void inserirCliente() throws DAOException {
		
		clienteDAO.insertCliente(getCliente());
		
	}
	
	public void atualizarCliente() throws DAOException {
		
		clienteDAO.updateCliente(getCliente());
		
	}
	
	public void excluirCliente() throws DAOException {
		
		clienteDAO.deleteCliente(getCliente());
		
	}
	
	public void buscarCliente() throws DAOException {
		
		String nome = this.nomeCliente.get();
		clientes.clear();
		clientes.addAll(clienteDAO.selectByName(nome));
		
	}
	
	public ObservableList<Cliente> getClientes() {
		return clientes;
	}
	public StringProperty getCpfClienteProperty() {
		return cpfCliente;
	}
	public StringProperty getNomeClienteProperty() {
		return nomeCliente;
	}
	public ObjectProperty<LocalDate> getNascimentoClienteProperty() {
		return nascimentoCliente;
	}
	public StringProperty getLogradouroClienteProperty() {
		return logradouroCliente;
	}
	public IntegerProperty getNumLograClienteProperty() {
		return numLograCliente;
	}
	public StringProperty getComplementoProperty() {
		return complemento;
	}
	public StringProperty getTelefoneClienteProperty() {
		return telefoneCliente;
	}
	public StringProperty getEmailClienteProperty() {
		return emailCliente;
	}
}
