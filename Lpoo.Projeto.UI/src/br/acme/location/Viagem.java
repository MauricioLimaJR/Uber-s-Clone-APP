package br.acme.location;

import java.io.Serializable;

import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class Viagem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private long clienteID;
	private long motoristaID;
	private Lugar origem;
	private Lugar destino;
	private double valorViagem;
	private String formaPagamento;
	
	public Viagem(Solicitante cliente, Motorista motorista, Lugar origem, Lugar destino, double valorViagem,
			String formaPagamento) {
		this.clienteID = cliente.getId();
		this.motoristaID = motorista.getId();
		this.origem = origem;
		this.destino = destino;
		this.valorViagem = valorViagem;
		this.formaPagamento = formaPagamento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClienteId() {
		return clienteID;
	}

	public void setClienteId(Solicitante cliente) {
		this.clienteID = cliente.getId();
	}

	public long getMotorista() {
		return motoristaID;
	}

	public void setMotorista(Motorista motorista) {
		this.motoristaID = motorista.getId();
	}

	public Lugar getOrigem() {
		return origem;
	}

	public void setOrigem(Lugar origem) {
		this.origem = origem;
	}

	public Lugar getDestino() {
		return destino;
	}

	public void setDestino(Lugar destino) {
		this.destino = destino;
	}

	public double getValorViagem() {
		return valorViagem;
	}

	public void setValorViagem(double valorViagem) {
		this.valorViagem = valorViagem;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
}
