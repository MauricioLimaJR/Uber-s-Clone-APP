package br.acme.users;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.storage.IRepositorio;
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
	private String numeroCelular;
	private double saldo=0.0;

	

	public Solicitante(String cpf, String nome, String senha, String sexo, String data, String email, String numeroCelular) throws ParseException, NullStringException, UnableCpfExecption {
		super(cpf, nome, senha, sexo);
		setDataNascimento(data);
		setEmail(email);
		setNumeroCelular(numeroCelular);
	}
	
	///////////// GETTERS AND SETTERS /////////////
	
	public java.sql.Date getDataNascimento() {
		java.sql.Date dataSql = new java.sql.Date(dataNascimento.getTime());
		return dataSql;
	}
	
	public String getDataNascimentoString(){
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
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

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular2) {
		this.numeroCelular = numeroCelular2;
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
	
	
	
	/////////////  METHODS  /////////////
	
	public void adicionarLugarFavorito(Lugar novo) {
		for(int i=0; i < lugaresFavoritos.length; i++){
                    if(lugaresFavoritos[i] == null){
                        lugaresFavoritos[i] = novo;
                        break;
                    }
                }
	}
	
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void solicitarCarona(IRepositorio<Motorista> motoristas, IRepositorio<Viagem> lugares, Lugar origem, Lugar destino, Double pagamento, String formaPagamento) throws RepositorioException{
		Boolean status = true;
		ArrayList<Motorista> relacaoMotoristas = motoristas.buscarTodos();
		for(Motorista driver : relacaoMotoristas){
			if(driver.responderPedido(lugares, this, driver, origem, destino, pagamento, formaPagamento)){				
				
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
		
	}
	
	public String toString(){
		String mensagem="";
		mensagem += "Id = "+this.getId()+" / Nome: "+this.getNome()+"; CPF: "+this.getCpf()+"; Sexo: "+this.getSexo();
		mensagem += "; Data de nascimento: "+this.getDataNascimento()+"; Email: "+getEmail()+"; Telefone: "+this.getNumeroCelular();
		return mensagem;
	}
	
}
