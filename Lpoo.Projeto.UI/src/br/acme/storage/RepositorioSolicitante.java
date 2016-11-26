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
public class RepositorioSolicitante implements IRepositorio, Serializable{

	private long nextId=0;
	private int quantiaArray=0;
	private Solicitante[] solicitantes = new Solicitante[50];
	
	public int getQuantiaArray(){
		return this.quantiaArray;
	}
	
	public void adicionar(Solicitante novo) throws RepositorioException{
	//public void adicionar(Solicitante novo) throws RepositorioException{
		if(quantiaArray < solicitantes.length){
			if(!verificarExistencia(novo.getCpf())){
				novo.setId(++nextId);
				solicitantes[quantiaArray] = novo;
				quantiaArray++;
				System.out.println(novo.getNome() + " foi adicionado na lista de solicitantes!");
			}
		}
		else
			throw new RepositorioException("Repositório de motoristas cheio.");
	}
	
	public Boolean verificarExistencia(String cpf) throws RepositorioException{
		for(int i = 0; i < quantiaArray; i++){
			if(solicitantes[i].getCpf().equals(cpf)){
				throw new RepositorioException("Motorista já cadastrado.");
			}
		}
		return false;
	}
	
	public void remover(long id) throws RepositorioException{
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");		
		Boolean status=true;
		for(int i=0; i<quantiaArray; i++){
			if(solicitantes[i].getId()==id){
				solicitantes[i] = solicitantes[quantiaArray-1];
				solicitantes[quantiaArray-1]=null;
				quantiaArray--;
				System.out.println("Solicitante removido!");
				status=false;
				break;
			}
		}
		if(status)throw new RepositorioException("Solicitante não encontrado.");
	}
	
	public void alterar(Solicitante atual) throws ParseException, RepositorioException, NullStringException, UnableCpfExecption{
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");
		verificarExistencia(atual.getCpf());
		System.out.println(atual.getNome()+", quais parâmetros você deseja alterar?");
		System.out.print(" 1. CPF \n 2. Email \n 3. senha \n 4. nome \n 5. sexo \n 6. data nascimento \n 7. fone\n 0. Finalizar!\n");
		Scanner input = new Scanner(System.in);
		int entrada, elementoNumerico;
		String elementoString;
		ArrayList<Integer> entradas = new ArrayList<Integer>();
		do{
			entrada = input.nextInt(); 
			if(entrada >= 1 && entrada <= 7){
				entradas.add(entrada);
			}
			else if(entrada == 0) break;
			else {System.out.println("Entrada inválida!"); break;}
		}
		while(true);
		
		String limpaBuffer =input.nextLine(); //Limpamos o buffer após utilizar o nextInt()
		
		// abaixo, realizamos as alterações desejadas
		
		 int numberOfChooses=entradas.size(), doIt=0;
		
		while(numberOfChooses>0)
			try {
				{
				  
					
					try {
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
						case 6:
							System.out.println("Digite sua data de nascimento:");
							elementoString = input.nextLine();
							atual.setDataNascimento(elementoString);//Digitar a data com "/" (ex: "01/02/2003")
							break;
						case 7:
							System.out.println("Digite seu número de telefone:");
							elementoNumerico = input.nextInt();
							atual.setNumeroCelular(elementoNumerico);
							System.out.println(atual.getNumeroCelular());
							limpaBuffer =input.nextLine();
							break;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					numberOfChooses--;
					doIt++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		input.close();
		if(doIt>0){ System.out.println("Alterações enviadas!"); }
	}
	
	// OS MÉTODOS "BUSCAR" E "BUSCARTODOS", NO MOMENTO, IMPRIMEM E RETONAR OS RESULTADOS
	// POIS A DUPLA ACHOU CONVINIETE ATÉ QUE TENHAMOS MAIS INFORMAÇÕES SOBRE ESSES MÉTODOS
	
	@SuppressWarnings("unchecked")
	public Solicitante buscar(long chaveId) throws RepositorioException{
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");
		boolean findIt=false;
		int i = 0;
		for(; i < this.quantiaArray; i++){
			if(solicitantes[i].getId()==chaveId){
				System.out.println(solicitantes[i].getNome()+" foi achado pelo Id: "+solicitantes[i].getId());
				findIt=true;
				break;
			}
		}
		if(findIt==false)throw new RepositorioException("Solicitante não existe.");
		else return solicitantes[i];
	}

	
	@SuppressWarnings("unchecked")
	public Solicitante[] buscarTodos() throws RepositorioException{
		if(quantiaArray==0)throw new RepositorioException("Repositório vazio.");
		for(int i = 0; i < this.quantiaArray; i++){
				System.out.println(this.solicitantes[i].getId());
		}
		return this.solicitantes;
	}

	//@Override
	//public <T> void adicionar(T novo) throws RepositorioException {
		// TODO Auto-generated method stub
		//this.adicionar(novo);
	//}

	@Override
	public <T> Boolean verificarExistencia(T t) throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void alterar(T atual) throws RepositorioException, NullStringException, UnableCpfExecption {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean verificarExistencia(Object t) throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(Object atual) throws RepositorioException, NullStringException, UnableCpfExecption {
		// TODO Auto-generated method stub
		
	}
}
