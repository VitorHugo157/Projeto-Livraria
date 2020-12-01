package entity;

import java.time.LocalDate;

public class Livro {
	
	private int codigoLivro;
	private String nomeLivro;
	private String idiomaLivro;
	private LocalDate anoLancamento;
	
	
	public Livro() {}
	
	public int getCodigoLivro() {
		return codigoLivro;
	}
	
	public void setCodigoLivro(int codigoLivro) {
		this.codigoLivro = codigoLivro;
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

	@Override
	public String toString() {
		return "Livro [codigoLivro=" + codigoLivro + ", nomeLivro=" + nomeLivro + ", idiomaLivro=" + idiomaLivro
				+ ", anoLancamento=" + anoLancamento + "]";
	}
}
