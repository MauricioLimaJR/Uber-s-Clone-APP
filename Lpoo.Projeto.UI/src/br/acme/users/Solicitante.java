package br.acme.users;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.storage.IRepositorioMotorista;
import br.acme.storage.IRepositorioViagem;
import br.acme.storage.RepositorioMotorista;
import br.acme.storage.RepositorioViagem;

public class Solicitante extends Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date dataNascimento;
	private String email;
	private long idCarona;
    private Lugar[] lugaresFavoritos = new Lugar[50];
	private int numeroCelular;
	private double saldo=0.0;
	private IRepositorioViagem viagensFeitas = new RepositorioViagem();
	

	public Solicitante(String cpf, String nome, String senha, String sexo, String data, String email, int numeroCelular) throws ParseException, NullStringException, UnableCpfExecption {
		super(cpf, nome, senha, sexo);
		setDataNascimento(data);
		setEmail(email);
		setNumeroCelular(numeroCelular);
	}
	
	///////////// GETTERS AND SETTERS /////////////
	
	public String getDataNascimento() {
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		
		//System.out.println(dataFormata.format(this.dataNascimento));
		return dataFormatada.format(this.dataNascimento);
	}

	public void setDataNascimento(String dataNascimento) throws ParseException {		
		DateFormat formatoData = DateFormat.getDateInstance();
		Date data = formatoData.parse(dataNascimento);
		this.dataNascimento = data;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws NullStringException {
		if(email==null||email.equals(""))throw new NullStringException("Email"); 
		else this.email = email;

	}	

	public int getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(int numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	

	public Lugar[] getLugaresFavoritos() {
		return lugaresFavoritos;
	}

	public void setLugarFavorito(Lugar[] novo) {
		lugaresFavoritos = novo;
	}

	public double getSaldo(){
		return saldo;
	}
	
	public void setSaldo(Double valor){
		saldo += valor;
	}
	
	public IRepositorioViagem getViagens() {
		return viagensFeitas;
	}

	public void setViagemFeita(IRepositorioViagem nova) {
		viagensFeitas = nova;
	}
	
	/////////////  METHODS  /////////////
	
	public void adicionarLugarFavorito(Lugar novo) {
		for(int i=0; i < lugaresFavoritos.length; i++){
                    if(lugaresFavoritos[i] == null){
                        lugaresFavoritos[i] = novo;
                        break;
                    }
                }
	}
	
	public void adicionarViagemFeita(Viagem ultima) throws RepositorioException {
		this.viagensFeitas.adicionar(ultima);
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void solicitarCarona(IRepositorioMotorista lista, IRepositorioViagem viagens, Lugar origem, Lugar destino, Double pagamento, String formaPagamento) throws RepositorioException{
		Boolean status = true;
		Motorista[] relacaoMotoristas = lista.buscarTodos();
		for(int i=0; i < lista.getQuantiaArray(); i++){
			if(relacaoMotoristas[i].responderPedido(viagens, this, relacaoMotoristas[i], origem, destino, pagamento, formaPagamento)){				
				
				status = false;
				break;
			}
		}
		if(status){
			System.out.println("Não há motoristas disponíveis!");
		}
	}
	
	public void removerCarona(RepositorioMotorista lista) throws RepositorioException{
		Motorista motoristaCancelado = lista.buscar(idCarona);
		motoristaCancelado.setDisponivel(true);
	}
	
	public void historico() throws RepositorioException{
		System.out.println(this.getNome()+" fez:");
		this.viagensFeitas.buscarTodos();
	}
	
	public String toString(){
		String mensagem="";
		mensagem += "Id = "+this.getId()+" / Nome: "+this.getNome()+"; CPF: "+this.getCpf()+"; Sexo: "+this.getSexo();
		mensagem += "; Data de nascimento: "+this.getDataNascimento()+"; Email: "+getEmail()+"; Telefone: "+this.getNumeroCelular();
		return mensagem;
	}
	
}
