package entity;

import java.time.LocalDate;

public class Edicao {
	private int isbn;
	private double precoEdicao;
	private LocalDate anoEdicao;
	private int numPaginas;
	private int qtdEstoque;
	
	public Edicao() {}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public double getPrecoEdicao() {
		return precoEdicao;
	}

	public void setPrecoEdicao(Double precoEdicao) {
		this.precoEdicao = precoEdicao;
	}

	public LocalDate getAnoEdicao() {
		return anoEdicao;
	}

	public void setAnoEdicao(LocalDate anoEdicao) {
		this.anoEdicao = anoEdicao;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	@Override
	public String toString() {
		return "Edicao [isbn=" + isbn + ", precoEdicao=" + precoEdicao + ", anoEdicao=" + anoEdicao + ", numPaginas="
				+ numPaginas + ", qtdEstoque=" + qtdEstoque + "]";
	}
}
