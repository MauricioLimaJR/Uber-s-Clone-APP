package br.acme.location;

import java.io.Serializable;

public class Lugar implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String identificadorLugar;
	private String endereco;
	
	public Lugar(String lugar, String place){
		identificadorLugar = lugar;
		endereco = place;
	}

	public String getIdentificadorLugar() {
		return identificadorLugar;
	}

	public void setIdentificadorLugar(String identificadorLugar) {
		this.identificadorLugar = identificadorLugar;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
