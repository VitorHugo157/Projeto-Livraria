package entity;

import java.time.LocalDate;

public class EdicaoEditoraLivro {
	
	private int isbnEdicao;
	private int codigoEditora;
	private int codigoLivro;
	private double precoEdicao;
	private LocalDate anoEdicao;
	private String nomeEditora;
	private String telefoneEditora;
	private String nomeLivro;
	private String idiomaLivro;
	private LocalDate anoLancamento;
	
	public double getPrecoEdicao() {
		return precoEdicao;
	}

	public void setPrecoEdicao(double precoEdicao) {
		this.precoEdicao = precoEdicao;
	}

	public LocalDate getAnoEdicao() {
		return anoEdicao;
	}

	public void setAnoEdicao(LocalDate anoEdicao) {
		this.anoEdicao = anoEdicao;
	}

	public String getNomeEditora() {
		return nomeEditora;
	}

	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}

	public String getTelefoneEditora() {
		return telefoneEditora;
	}

	public void setTelefoneEditora(String telefoneEditora) {
		this.telefoneEditora = telefoneEditora;
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

	public EdicaoEditoraLivro() {}

	public int getIsbnEdicao() {
		return isbnEdicao;
	}

	public void setIsbnEdicao(int isbnEdicao) {
		this.isbnEdicao = isbnEdicao;
	}

	public int getCodigoEditora() {
		return codigoEditora;
	}

	public void setCodigoEditora(int codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public int getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(int codigoLivro) {
		this.codigoLivro = codigoLivro;
	}
}
