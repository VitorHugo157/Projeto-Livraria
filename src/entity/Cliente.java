package entity;

import java.time.LocalDate;

public class Cliente {
	private String cpfCliente;
	private String nomeCliente;
	private LocalDate nascimentoCliente;
	private String logradouroCliente;
	private int numLograCliente;
	private String complemento;
	private String telefone;
	private String email;
	
	public Cliente() {}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public LocalDate getNascimentoCliente() {
		return nascimentoCliente;
	}

	public void setNascimentoCliente(LocalDate nascimentoCliente) {
		this.nascimentoCliente = nascimentoCliente;
	}

	public String getLogradouroCliente() {
		return logradouroCliente;
	}

	public void setLogradouroCliente(String logradouroCliente) {
		this.logradouroCliente = logradouroCliente;
	}

	public int getNumLograCliente() {
		return numLograCliente;
	}

	public void setNumLograCliente(int numLograCliente) {
		this.numLograCliente = numLograCliente;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Cliente [cpfCliente=" + cpfCliente + ", nomeCliente=" + nomeCliente + ", nascimentoCliente="
				+ nascimentoCliente + ", logradouroCliente=" + logradouroCliente + ", numLograCliente="
				+ numLograCliente + ", complemento=" + complemento + ", telefone=" + telefone + ", email=" + email
				+ "]";
	}
}
