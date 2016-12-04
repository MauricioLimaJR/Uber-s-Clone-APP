package br.acme.storage;

import java.io.Serializable;
import java.util.ArrayList;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.location.Viagem;

@SuppressWarnings("serial")
public class RepositorioViagem implements IRepositorio<Viagem>, Serializable{

	private int nextId=0;
	private ArrayList<Viagem> viagens = new ArrayList<Viagem>();
	
	public int getQuantiaArray() {
		return nextId;
	}
	
	public void adicionar(Viagem obj) throws RepositorioException{
		if(!verificarExistencia(obj)){
			obj.setId(++nextId);
			viagens.add(obj);
			//@PrintString
			System.out.println("A viagem de id:"+obj.getId()+ ", foi adicionada na lista de viagens!");
		}
		else
			throw new RepositorioException("Viagem já cadastrado!");
	}
	
	public Boolean verificarExistencia(Viagem obj) throws RepositorioException {
		if(nextId==0)throw new RepositorioException("Repositório vazio.");	
		long id = obj.getId();
				
		for(Viagem travel : viagens){
			if(travel.getId() == id){
				viagens.remove(travel);
				//@PrintString
				throw new RepositorioException("Viagem já cadastrada.");
				//return true;
			}
		}
		return false;
	}
	
	public Boolean remover(Viagem obj) throws RepositorioException{
		//@PrintString
		if(nextId==0)throw new RepositorioException("Repositório vazio.");	
		long id = obj.getId();
						
		for(Viagem travel : viagens){
			if(travel.getId() == id){
				viagens.remove(travel);
				//@PrintString
				System.out.println(travel.getId() + " foi removido da lista de viagens!");	
				//return true;
			}
		}

		//@PrintString
		System.out.println("Id viagem inválida!");
		throw new RepositorioException("Viagem não encontrada.");
	}
	
	public Viagem buscar(long id) throws RepositorioException{
		if(nextId==0)throw new RepositorioException("Repositório vazio.");

		for(Viagem travel : viagens){
			if(travel.getId() == id){
				System.out.println("A viagem foi achada pelo Id: "+travel.getId());
				return travel;
			}
		}
		throw new RepositorioException("Viagem não encontrada.");
	}
	
	public ArrayList<Viagem> buscarPorId(long id) throws RepositorioException{
	
		ArrayList<Viagem> list = new ArrayList<Viagem>();
		
		for(Viagem travel : viagens){
			if(travel.getClienteId() == id){
				list.add(travel);
			}
		}
		if(list.isEmpty()){
		throw new RepositorioException("Viagem não encontrada.");
		}
		
		return list;
	}

	public ArrayList<Viagem> buscarTodos() throws RepositorioException{
		//@PrintString
		if(nextId==0)throw new RepositorioException("Repositório vazio.");
		
		/*
		 * The method code below prints all users found
		 *
		System.out.println("Viagens realizadas: ");
		for(Viagem travel: viagens){
			System.out.println(travel.getId());
		}
		}
		*/
		
		return viagens;
	}

	@Override
	public void alterar(Viagem obj) throws RepositorioException, NullStringException, UnableCpfExecption {
		// TODO Auto-generated method stub
		
	}

}
