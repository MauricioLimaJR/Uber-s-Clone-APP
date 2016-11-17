package br.acme.storage;

import java.io.Serializable;

import br.acme.exception.RepositorioException;
import br.acme.location.Viagem;

@SuppressWarnings("serial")
public class RepositorioViagem implements IRepositorioViagem, Serializable{

	private int nextId=0;
	private int quantiaArray=0;
	private Viagem[] viagens = new Viagem[50];
	
	public void adicionar(Viagem nova) throws RepositorioException{
		if(quantiaArray < viagens.length){
			if(!verificarExistencia(nova.getId())){
				nova.setId(++nextId);
				viagens[quantiaArray] = nova;
				quantiaArray++;
				System.out.println("A viagem com id: "+nova.getId() + ", foi adicionada na lista de viagens!");
			}
		}
		else
			throw new RepositorioException("Repositório de viagens cheio.");
	}
	
	public Boolean verificarExistencia(long id) throws RepositorioException{
		for(int i = 0; i < quantiaArray; i++){
			if(viagens[i].getId()==id){
				throw new RepositorioException("Viagem já cadastrada.");
			}
		}
		return false;
	}
	
	public void remover(long id) throws RepositorioException{
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");
		Boolean status=true;
		for(int i=0; i<quantiaArray; i++){
			if(viagens[i].getId()==id){
				viagens[i] = viagens[quantiaArray-1];
				viagens[quantiaArray-1]=null;
				quantiaArray--;
				System.out.println("Viagem removida!");
				status=false;
				break;
			}
		}
		if(status)throw new RepositorioException("Viagem não encontrada.");
	}
	
	public Viagem buscar(long chaveId) throws RepositorioException{
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");
		boolean findIt=false;
		int i=0;
		for(; i < this.quantiaArray; i++){
			if(viagens[i].getId()==chaveId){
				System.out.println("A viagem " + viagens[i].getId() + " foi achada na lista de viagens!");
				findIt = true;
				break;
			}
		}
		if(findIt==false)throw new RepositorioException("Viagem não existe.");
		return viagens[--i];
	}

	
	public Viagem[] buscarTodos(){
		System.out.println("Viagens realizadas: ");
		for(int i = 0; i < this.quantiaArray; i++){
        	System.out.println("Viagem de id: " + this.viagens[i].getId() + "/ De: "+this.viagens[i].getOrigem().getEndereco()+" - Para: "+this.viagens[i].getDestino().getEndereco()+".");                      
		}
		return this.viagens;
	}	
}
