package entity;

import java.time.LocalDate;

public class ClienteLivro {
	
	private String cpfCliente;
	private int codigoLivro;
	private String nomeCliente;
	private String nomeLivro;
	private String idiomaLivro;
	private LocalDate anoLancamento;
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public String getIdiomaLivro() {
		return idiomaLivro;
	}

	public void setIdiomaLivro(String idiomaLivro) {
		this.idiomaLivro = idiomaLivro;
	}

	public LocalDate getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(LocalDate anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public ClienteLivro() {}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public int getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(int codigoLivro) {
		this.codigoLivro = codigoLivro;
	}
}
