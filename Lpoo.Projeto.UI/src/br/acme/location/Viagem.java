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
	private String source;
	private String destiny;
	public long getClienteID() {
		return clienteID;
	}

	public void setClienteID(long clienteID) {
		this.clienteID = clienteID;
	}

	public long getMotoristaID() {
		return motoristaID;
	}

	public void setMotoristaID(long motoristaID) {
		this.motoristaID = motoristaID;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestiny() {
		return destiny;
	}

	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

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
	
	public Viagem(long idCliente, long idMotorista, Lugar origem, Lugar destino, double valorViagem,
			String formaPagamento) {
		this.clienteID = idCliente;
		this.motoristaID = idMotorista;
		this.origem = origem;
		this.destino = destino;
		this.valorViagem = valorViagem;
		this.formaPagamento = formaPagamento;
		this.source = origem.getEndereco();
		this.destiny = destino.getEndereco();
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

	public long getMotoristaId() {
		return motoristaID;
	}

	public void setMotoristaId(Motorista motorista) {
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
