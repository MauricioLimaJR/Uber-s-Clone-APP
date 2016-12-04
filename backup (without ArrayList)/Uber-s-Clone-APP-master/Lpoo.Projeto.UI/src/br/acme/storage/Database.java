package br.acme.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Database{

	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	
	/*
	 * Method to save data
	 */
	
	public static <Tipo> void saveStatus(Tipo object, String directory){		
		//@PrintString
		try{
			output = new ObjectOutputStream(new FileOutputStream(directory));
		}
		catch(IOException e){
			System.out.println("Erro em abrir o arquivo");
		}
		finally{
			System.out.println("primeira parte");
		}
		
		try {
			output.writeObject(object);
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

	/*
	 * Methods to read data
	 */
	
	@SuppressWarnings("unchecked")
	public static <Tipo> Tipo readDataBase(String directory){
		
		Tipo lista = null;
		
		//Open the directory
		//@PrintString
		try{
			input = new ObjectInputStream(new FileInputStream(directory));
		}
		catch(IOException e){
			System.out.println("Erro ao abrir o arquivo");
			e.getMessage();
		}
		
		//Read the file
		//@PrintString
		
		try {
			lista =   (Tipo) input.readObject();
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

}
