package br.acme.storage;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.users.Solicitante;

@SuppressWarnings({ "unused", "serial" })
public class RepositorioSolicitante implements IRepositorio<Solicitante>, Serializable{

	private long nextId=0;
	private int quantiaArray=0;
	private Solicitante[] solicitantes = new Solicitante[50];
	
	public int getQuantiaArray(){
		return this.quantiaArray;
	}
	
	public void adicionar(Solicitante obj) throws RepositorioException{
		if(this.quantiaArray < solicitantes.length){
			if(!verificarExistencia(obj)){
				obj.setId(++nextId);
				solicitantes[quantiaArray] = obj;
				quantiaArray++;
				//@PrintString
				System.out.println(obj.getNome() + " foi adicionado na lista de solicitantes!");
			}
		}
		else
			throw new RepositorioException("Repositório de motoristas cheio.");
	}
	
	//@Override
		public Boolean verificarExistencia(Solicitante obj) throws RepositorioException {
			for(int i = 0; i < quantiaArray; i++){
				if(solicitantes[i].getCpf().equals(obj.getCpf())){
					throw new RepositorioException("Usuário já cadastrado.");
				}
			}
			return false;
		}

	
		public void remover(Solicitante obj) throws RepositorioException{
		//@PrintString
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");		
		Boolean status=true;
		
		for(int i=0; i<quantiaArray; i++){
			if(solicitantes[i].getId()==obj.getId()){
				solicitantes[i] = solicitantes[quantiaArray-1];
				solicitantes[quantiaArray-1]=null;
				quantiaArray--;
				//@PrintString
				System.out.println("Solicitante removido!");
				status=false;
				break;
			}
		}
		//@PrintString
		if(status)throw new RepositorioException("Solicitante não encontrado.");
	}
	
		public void alterar(Solicitante obj) throws RepositorioException, NullStringException, UnableCpfExecption{
		//@PrintString
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");
		verificarExistencia(obj);
		
		
	}
	
	
	public Solicitante buscar(long  id) throws RepositorioException{
		//@PrintString
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");
		boolean findIt=false;		
		int i=0;
		for(; i < this.quantiaArray; i++){
			if(solicitantes[i].getId()==id){
				//@PrintString
				System.out.println(solicitantes[i].getNome()+" foi achado pelo Id: "+solicitantes[i].getId());
				return solicitantes[i];
			}
		}
		//@PrintString
		throw new RepositorioException("Solicitante não existe.");
	}

	public Solicitante[] buscarTodos() throws RepositorioException{
		//@PrintString
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");
		
		/*
		 * The method "for" below prints all users found
		 *
		for(int i = 0; i < this.quantiaArray; i++){
				//@PrintString
				System.out.println(this.solicitantes[i].getId());
		}
		*/
		
		return this.solicitantes;
	}

}
