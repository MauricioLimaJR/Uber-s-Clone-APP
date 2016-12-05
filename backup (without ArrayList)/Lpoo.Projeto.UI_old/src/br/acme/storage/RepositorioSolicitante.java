package br.acme.storage;
import java.io.Serializable;
import java.util.ArrayList;

import br.acme.exception.RepositorioException;
import br.acme.users.Solicitante;

@SuppressWarnings("serial")
public class RepositorioSolicitante implements IRepositorio<Solicitante>, Serializable{

	private long nextId=0;
	private ArrayList<Solicitante> solicitantes = new ArrayList<Solicitante>();
	
	public void adicionar(Solicitante obj) throws RepositorioException{
		if(!verificarExistencia(obj)){
			obj.setId(++nextId);
			solicitantes.add(obj);
			//@PrintString
			System.out.println(obj.getNome() + " foi adicionado na lista de solicitantes!");
		}
		else
			throw new RepositorioException("Solicitante j� cadastrado!");
	}
	
	
		public Boolean verificarExistencia(Solicitante obj) throws RepositorioException {
			String objCpf = obj.getCpf();
			for(Solicitante user : solicitantes){
				if(user.getCpf().equals(objCpf)){
					throw new RepositorioException("Usu�rio j� cadastrado.");
				}
			}
			return false;
		}
		
	public Boolean remover(Solicitante obj) throws RepositorioException{
		//@PrintString
		if(nextId==0)throw new RepositorioException("Reposit�rio vazio.");	
		long id = obj.getId();
		
		for(Solicitante user : solicitantes){
			if(user.getId() == id){
				solicitantes.remove(user);
				//@PrintString
				System.out.println(user.getNome() + " foi removido da lista de solicitantes!");	
				return true;
			}
			
		}
		
		//@PrintString
		System.out.println("Id solicitante inv�lida!");
		throw new RepositorioException("Solicitante n�o encontrado.");
	}
	
	
	public void alterar(Solicitante obj) throws RepositorioException{
		if(solicitantes.contains(obj)){
			solicitantes.remove(obj);
			solicitantes.add(obj);
			//@PrintString
			System.out.println(obj.getId()+ ": Altera��es enviadas!"); 
		}
	}
	
	public Solicitante buscar(long  id) throws RepositorioException{
		
		if(nextId==0)throw new RepositorioException("Reposit�rio vazio.");

		for(Solicitante user : solicitantes){
			if(user.getId() == id){
				System.out.println(user.getNome()+" foi achado pelo Id: "+user.getId());
				return user;
			}
		}
		throw new RepositorioException("Solicitante n�o existe.");
	}
	
	
	public ArrayList<Solicitante> buscarTodos() throws RepositorioException{
		//@PrintString
		if(nextId==0)throw new RepositorioException("Reposit�rio vazio.");
		
		/*
		 * The method code below prints all users found
		 *
		System.out.println("Solicitantes:");
		for(Solicitante user: solicitantes){
			System.out.println(user.getNome());
		}
		}
		*/
		
		return this.solicitantes;
	}


	@Override
	public int getQuantiaArray() {
		// TODO Auto-generated method stub
		return 0;
	}



}
