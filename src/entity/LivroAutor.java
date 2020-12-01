package entity;

import java.time.LocalDate;

public class LivroAutor {
	
	private int codigoLivro;
	private int codigoAutor;
	private String nomeLivro;
	private String idiomaLivro;
	private LocalDate anoLancamento;
	private String nomeAutor;
	private String nacionalidade;
	
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

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public LivroAutor() {}

	public int getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(int codigoLivro) {
		this.codigoLivro = codigoLivro;
	}

	public int getCodigoAutor() {
		return codigoAutor;
	}

	public void setCodigoAutor(int codigoAutor) {
		this.codigoAutor = codigoAutor;
	}
}
