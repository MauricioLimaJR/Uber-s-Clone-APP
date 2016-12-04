package br.acme.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.acme.users.Gerente;

public class SolicitationDB {

	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	
	//Método para salvar um arquivo
	public static void salvarDados(String nomeArq, String extensao, Object objeto){		
		try{
			output = new ObjectOutputStream(new FileOutputStream(nomeArq+extensao));
		}
		catch(IOException e){
			System.out.println("Erro em abrir o arquivo");
		}
		finally{
			System.out.println("primeira parte");
		}
		
		try {
			output.writeObject(objeto);
			System.out.println("Salvo com sucesso!");
		} 
		catch (IOException e) {
			System.out.println("Erro ao escrever no arquivo:");
			e.printStackTrace();
		}
		finally{
			try{
				if(output != null)
					output.close();
			}
			catch(IOException e){
				System.out.println("Erro ao fechar o arquivo");
				System.exit(1);
			}
		}
	}
	
	//Métodos que utilizam o "salvarDados"	
	public static void salvarEstado(IRepositorio listaDeSolicitantes, String posicao){
		salvarDados("Database/repositorio solicitantes"+posicao, ".txt", listaDeSolicitantes);
	}

	//Método para ler um arquivo
	@SuppressWarnings("unchecked")
	public static <T> T lerDados(String nomeArq, String extensao){
		T lista = null;
		
		//Abrir o arquivo
		try{
			input = new ObjectInputStream(new FileInputStream(nomeArq+extensao));
		}
		catch(IOException e){
			System.out.println("Erro ao abrir o arquivo");
			e.getMessage();
		}
		
		//Ler o arquivo
		
		try {
			lista =  (T) input.readObject();
			System.out.println("Objeto lido com sucesso");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Falha em criar o obejto");
			e.printStackTrace();
		}
		catch(IOException e){
			System.out.println("Erro durante a leitura do arquivo");
			e.printStackTrace();
		}
		finally{
			try{
				if(input != null)
					input.close();
			}
			catch(IOException e){
				System.out.println("Erro ao fechar o arquivo");
				System.exit(1);
			}
		}
		return lista;
	}

	public static IRepositorio LerBaseSolicitantes(String posicao){
		return (IRepositorio)lerDados("Database/repositorio solicitantes"+posicao, ".txt");
	}
}
