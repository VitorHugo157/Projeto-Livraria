package entity;

import java.time.LocalDate;

public class Autor {
	
	private int codigoAutor;
	private String nomeAutor;
	private LocalDate nascimentoAutor;
	private String nacionalidadeAutor;
	private String biografiaAutor;
	
	public Autor() {}

	public int getCodigoAutor() {
		return codigoAutor;
	}

	public void setCodigoAutor(int codigoAutor) {
		this.codigoAutor = codigoAutor;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public LocalDate getNascimentoAutor() {
		return nascimentoAutor;
	}

	public void setNascimentoAutor(LocalDate nascimentoAutor) {
		this.nascimentoAutor = nascimentoAutor;
	}

	public String getNacionalidadeAutor() {
		return nacionalidadeAutor;
	}

	public void setNacionalidadeAutor(String nacionalidadeAutor) {
		this.nacionalidadeAutor = nacionalidadeAutor;
	}

	public String getBiografiaAutor() {
		return biografiaAutor;
	}

	public void setBiografiaAutor(String biografiaAutor) {
		this.biografiaAutor = biografiaAutor;
	}

	@Override
	public String toString() {
		return "Autor [codigoAutor=" + codigoAutor + ", nomeAutor=" + nomeAutor + ", nascimentoAutor=" + nascimentoAutor
				+ ", nacionalidadeAutor=" + nacionalidadeAutor + ", biografiaAutor=" + biografiaAutor + "]";
	}
}
