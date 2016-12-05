package br.acme.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.users.Motorista;

public class RepositorioMotoristaT implements IRepositorio<Motorista>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long nextId=0;
	private int quantiaArray=0;
	private Motorista[] motoristas = new Motorista[50];
	
	public int getQuantiaArray(){
		return this.quantiaArray;
	}
		
	public void adicionar(Motorista novo)throws RepositorioException{
		if(quantiaArray < motoristas.length){
			if(!verificarExistencia(novo)){
				novo.setId(++nextId);
				motoristas[quantiaArray] = novo;
				quantiaArray++;
				System.out.println(novo.getNome() + " foi adicionado na lista de motoristas!");
			}
		}
		else
			throw new RepositorioException("Repositório de motoristas cheio.");
	}
	

	public Boolean verificarExistencia(Motorista obj) throws RepositorioException {
		String cpf = obj.getCpf();
		for(int i = 0; i < quantiaArray; i++){
			if(motoristas[i].getCpf().equals(cpf)){
				throw new RepositorioException("Motorista já cadastrado.");
			}
		}
		return false;
	}
	
	
	public void remover(long id) throws RepositorioException{
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");
		Boolean status=true;
		for(int i=0; i<quantiaArray; i++){
			if(motoristas[i].getId()==id){
				motoristas[i] = motoristas[quantiaArray-1];
				motoristas[quantiaArray-1]=null;
				quantiaArray--;
				System.out.println("Motorista removido!");
				status=false;
				break;
			}
		}
		if(status)throw new RepositorioException("Motorista não encontrado.");
	}
	
	public void alterar(Motorista atual) throws RepositorioException, NullStringException, UnableCpfExecption{
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");
		verificarExistencia(atual);
		System.out.println(atual.getNome()+", quais parâmetros você deseja alterar?");
		System.out.print(" 1. CPF \n 2. Email \n 3. senha \n 4. nome \n 5. sexo \n 0. Finalizar!\n");
		Scanner input = new Scanner(System.in);
		int entrada;
		String elementoString;
		ArrayList<Integer> entradas = new ArrayList<Integer>();
		do{
			entrada = input.nextInt(); 
			if(entrada >= 1 && entrada <= 5){
				entradas.add(entrada);
			}
			else if(entrada == 0) break;
			else {System.out.println("Entrada inválida!"); break;}
		}
		while(true);
		
		@SuppressWarnings("unused")
		String limpaBuffer = input.nextLine();
		
		//System.out.println(entradas);
		// abaixo, realizamos as alterações desejadas
		
		 int numberOfChooses=entradas.size(), doIt=0;
		
		while(numberOfChooses>0){
		  
			
			switch(entradas.get(doIt)){
			case 1: 
				System.out.println("Digite o novo CPF:");
				elementoString = input.nextLine();
				atual.setCpf(elementoString);
				break;
			case 2: 
				System.out.println("Digite o novo email:");
				elementoString = input.nextLine();
				atual.setEmail(elementoString);
				break;
			case 3:
				System.out.println("Digite sua nova senha:");
				elementoString = input.nextLine();
				atual.setSenha(elementoString);
				break;
			case 4:
				System.out.println("Digite seu novo nome:");
				elementoString = input.nextLine();
				atual.setNome(elementoString);
				break;
			case 5:
				System.out.println("Digite seu sexo:");
				elementoString = input.nextLine();
				atual.setSexo(elementoString);
				break;			
			}
			numberOfChooses--;
			doIt++;
		}
		input.close();
		if(doIt>0){ System.out.println("Alterações enviadas!"); }
	}
	
	// OS MÉTODOS "BUSCAR" E "BUSCARTODOS", NO MOMENTO, IMPRIMEM E RETONAR OS RESULTADOS
	// POIS A DUPLA ACHOU CONVINIETE ATÉ QUE TENHAMOS MAIS INFORMAÇÕES SOBRE ESSES MÉTODOS
	
	public Motorista buscar(long chaveId) throws RepositorioException{
		int i = 0;
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");
		boolean findIt=false;
		for(; i < this.quantiaArray; i++){
			if(motoristas[i].getId()==chaveId){
				System.out.println(motoristas[i].getNome()+" foi achado pelo Id: "+motoristas[i].getId());
				break;
			}
		}
		if(findIt==false)throw new RepositorioException("Viagem não existe.");
		return motoristas[i];
	}

	
	public Motorista[] buscarTodos()throws RepositorioException{
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");
		for(int i = 0; i < this.quantiaArray; i++){
				System.out.println(this.motoristas[i].getId()+" - "+this.motoristas[i].getNome()+" - "+this.motoristas[i].getDisponivel());
		}
		return this.motoristas;
	}


	@Override
	public void remover(Motorista obj) throws RepositorioException {
		// TODO Auto-generated method stub
		
	}
	
}
	