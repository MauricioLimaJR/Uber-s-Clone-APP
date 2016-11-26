package br.acme.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.acme.users.Gerente;

public class Database {

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
	public static void salvarEstado(IRepositorioMotorista listaDeMotoristas, String posicao){
		salvarDados("Database/repositorio motoristas"+posicao, ".txt", listaDeMotoristas);
	}

	public static void salvarEstado(IRepositorio listaDeSolicitantes, String posicao){
		salvarDados("Database/repositorio solicitantes"+posicao, ".txt", listaDeSolicitantes);
	}

	public static void salvarEstado(IRepositorioViagem listaDeViagens, String posicao){
		salvarDados("Database/repositorio viagens"+posicao, ".txt", listaDeViagens);
	}

	public static void salvarEstado(Gerente admin){
		salvarDados("Database/gerente", ".txt", admin);
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
	
	public static IRepositorioMotorista LerBaseMotoristas(String posicao){
		return (IRepositorioMotorista)lerDados("Database/repositorio motoristas"+posicao, ".txt");		
	}

	public static IRepositorioViagem LerBaseViagens(String posicao){
		return (IRepositorioViagem)lerDados("Database/repositorio viagens"+posicao, ".txt");
	}

	public static Gerente LerBaseGerente(){
		return (Gerente)lerDados("Database/gerente", ".txt");
	}
	
}
