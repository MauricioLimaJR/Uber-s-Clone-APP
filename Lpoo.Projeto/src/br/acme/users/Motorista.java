package br.acme.users;

import java.io.Serializable;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.storage.IRepositorioViagem;
//import br.acme.storage.RepositorioViagem;

public class Motorista extends Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean disponivel=true;
	private String email;
    
	private Viagem[] viagensFeitas = new Viagem[100];
   
	//TESTAR NO LUGAR DO VETOR DE VIAGENSFEITAS
	//private IRepositorioViagem viagensFeitas = new RepositorioViagem();
	
    public Motorista(String cpf, String nome, String senha, String sexo, String email) throws NullStringException, UnableCpfExecption {
		super(cpf, nome, senha, sexo);
		setEmail(email);
	}
    
    ///////////// GETTERS AND SETTERS /////////////

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws NullStringException {
		if(email==null||email.equals(""))throw new NullStringException("Email"); 
		else this.email = email;

	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Viagem[] getViagens() {
		return viagensFeitas;
	}

	public void setViagensFeitas(Viagem[] nova){
		this.viagensFeitas = nova;
	}
		
	/////////////  METHODS  /////////////
	

	public void adicionarViagemFeita(Viagem ultima) {
		for(int i=0; i < viagensFeitas.length; i++){
                    if(viagensFeitas[i] == null){
                        viagensFeitas[i] = ultima;
                        break;
                    }
                }
                
		//this.viagensFeitas.adicionar(ultima);
	}
	
	public Boolean responderPedido(IRepositorioViagem viagens, Solicitante cliente, Motorista atual, Lugar origem, Lugar destino, Double pagamento, String formaPagamento) throws RepositorioException{
		if(getDisponivel()){
			Viagem viagem = new Viagem(cliente, atual, origem, destino, pagamento, formaPagamento);
			setDisponivel(false);
			viagens.adicionar(viagem);
			cliente.adicionarViagemFeita(viagem);
			atual.adicionarViagemFeita(viagem);
			System.out.println("Viagem requerida com sucesso!");
			return true;
		}
		return false;
	}
	
	public void historico(){
		System.out.println(this.getNome()+" fez:");
		for(int i=0; i < viagensFeitas.length; i++){
                    if(viagensFeitas[i] != null){
                    	System.out.println("Viagem de id: " + viagensFeitas[i].getId() + " / De: "+viagensFeitas[i].getOrigem().getEndereco()+" - Para: "+viagensFeitas[i].getDestino().getEndereco()+".");
                    }
                }
	
		//this.viagensFeitas.buscarTodos();
	}
	
	public String toString(){
		String mensagem="";
		mensagem += "Id = "+this.getId()+" / Nome: "+this.getNome()+"; CPF: "+this.getCpf()+"; Senha: "+this.getSenha()+"; Sexo: "+this.getSexo();
		mensagem += "; Email: "+getEmail();
		return mensagem;
	}
	
}
