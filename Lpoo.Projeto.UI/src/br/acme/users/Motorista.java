package br.acme.users;

import java.io.Serializable;
import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioViagem;
//import br.acme.storage.RepositorioViagem;

public class Motorista extends Solicitante implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean disponivel=true;
  
   
	//TESTAR NO LUGAR DO VETOR DE VIAGENSFEITAS
	//private IRepositorioViagem viagensFeitas = new RepositorioViagem();
	
	
    public Motorista(Solicitante oldUser) throws NullStringException, UnableCpfExecption, ParseException{
		
    	super(oldUser.getCpf(), oldUser.getNome(), oldUser.getSenha(), oldUser.getSexo(),
				oldUser.getDataNascimentoString(), oldUser.getEmail(), oldUser.getNumeroCelular());
	}
    
    ///////////// GETTERS AND SETTERS /////////////

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}
		
	/////////////  METHODS  /////////////
	

	public void adicionarViagemFeita(Viagem ultima) throws RepositorioException {
		//this.viagensFeitas.adicionar(ultima);
	}
	
	public Boolean responderPedido(IRepositorio<Viagem> lugares, Solicitante cliente, Motorista atual, Lugar origem, Lugar destino, Double pagamento, String formaPagamento) throws RepositorioException{
		if(getDisponivel()){
			Viagem viagem = new Viagem(cliente, atual, origem, destino, pagamento, formaPagamento);
			setDisponivel(false);
			lugares.adicionar(viagem);
			//cliente.adicionarViagemFeita(viagem);
			atual.adicionarViagemFeita(viagem);
			System.out.println("Viagem requerida com sucesso!");
			return true;
		}
		return false;
	}
	
	public void historico() throws RepositorioException{
		System.out.println(this.getNome()+" fez:");
		//this.viagensFeitas.buscarTodos();
	}
	
	public String toString(){
		String mensagem="";
		mensagem += "Id = "+this.getId()+" / Nome: "+this.getNome()+"; CPF: "+this.getCpf()+"; Senha: "+this.getSenha()+"; Sexo: "+this.getSexo();
		mensagem += "; Email: "+getEmail();
		return mensagem;
	}
	
}
