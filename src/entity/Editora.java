package entity;

public class Editora {
	private int codigoEditora;
	private String nomeEditora;
	private String logradouroEditora;
	private int numLograEditora;
	private String cepEditora;
	private String telefoneEditora;
	
	public Editora() {}

	public int getCodigoEditora() {
		return codigoEditora;
	}

	public void setCodigoEditora(int codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public String getNomeEditora() {
		return nomeEditora;
	}

	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}

	public String getLogradouroEditora() {
		return logradouroEditora;
	}

	public void setLogradouroEditora(String logradouroEditora) {
		this.logradouroEditora = logradouroEditora;
	}

	public int getNumLograEditora() {
		return numLograEditora;
	}

	public void setNumLograEditora(int numLograEditora) {
		this.numLograEditora = numLograEditora;
	}

	public String getCepEditora() {
		return cepEditora;
	}

	public void setCepEditora(String cepEditora) {
		this.cepEditora = cepEditora;
	}

	public String getTelefoneEditora() {
		return telefoneEditora;
	}

	public void setTelefoneEditora(String telefoneEditora) {
		this.telefoneEditora = telefoneEditora;
	}

	@Override
	public String toString() {
		return "Editora [codigoEditora=" + codigoEditora + ", nomeEditora=" + nomeEditora + ", logradouroEditora="
				+ logradouroEditora + ", numLograEditora=" + numLograEditora + ", cepEditora=" + cepEditora
				+ ", telefoneEditora=" + telefoneEditora + "]";
	}
}
