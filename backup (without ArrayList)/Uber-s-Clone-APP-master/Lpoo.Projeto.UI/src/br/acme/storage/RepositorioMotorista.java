package br.acme.storage;

import java.io.Serializable;
import java.util.ArrayList;
import br.acme.exception.RepositorioException;
import br.acme.users.Motorista;

@SuppressWarnings("serial")
public class RepositorioMotorista implements IRepositorio<Motorista>, Serializable{

	private long nextId=0;
	private ArrayList<Motorista> motoristas = new ArrayList<Motorista>();
	
	public void adicionar(Motorista obj) throws RepositorioException{
		if(!verificarExistencia(obj)){
			obj.setId(++nextId);
			motoristas.add(obj);
			//@PrintString
			System.out.println(obj.getNome() + " foi adicionado na lista de solicitantes!");
		}
		else
			throw new RepositorioException("Motorista já cadastrado!");
	}
	
	
	
	public Boolean verificarExistencia(Motorista obj) throws RepositorioException {
			String objCpf = obj.getCpf();
			for(Motorista driver : motoristas){
				if(driver.getCpf().equals(objCpf)){
					throw new RepositorioException("Motorista já cadastrado.");
				}
			}
			return false;
	}
	
	public Boolean remover(Motorista obj) throws RepositorioException{
		//@PrintString
		if(nextId==0)throw new RepositorioException("Repositório vazio.");	
		long id = obj.getId();
		
		for(Motorista driver : motoristas){
			if(driver.getId() == id){
				motoristas.remove(driver);
				//@PrintString
				System.out.println(driver.getNome() + " foi removido da lista de motoristas!");	
				return true;
			}
			
		}
		
		//@PrintString
		System.out.println("Id do motorista inválida!");
		throw new RepositorioException("Motorista não encontrado.");
	}
	
	
	public void alterar(Motorista obj) throws RepositorioException{
		if(motoristas.contains(obj)){
			motoristas.remove(obj);
			motoristas.add(obj);
			//@PrintString
			System.out.println(obj.getId()+ ": Alterações enviadas!"); 
		}
	}
		
	public Motorista buscar(long  id) throws RepositorioException{
		
		if(nextId==0)throw new RepositorioException("Repositório vazio.");

		for(Motorista driver : motoristas){
			if(driver.getId() == id){
				System.out.println(driver.getNome()+" foi achado pelo Id: "+driver.getId());
				return driver;
			}
		}
		throw new RepositorioException("Motorista não cadastrado.");
	}
	
	
	public ArrayList<Motorista> buscarTodos() throws RepositorioException{
		//@PrintString
		if(nextId==0)throw new RepositorioException("Repositório vazio.");
		
		/*
		 * The method code below prints all users found
		 *
		System.out.println("Solicitantes:");
		for(Solicitante user: solicitantes){
			System.out.println(user.getNome());
		}
		}
		*/
		
		return this.motoristas;
	}


	@Override
	public int getQuantiaArray() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
	