package br.acme.tests;

import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.storage.Database;
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioMotorista;
import br.acme.storage.RepositorioSolicitante;
import br.acme.storage.RepositorioViagem;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class TesteDancoDeDados {

	public static void main(String[] args) throws ParseException, RepositorioException, NullStringException, UnableCpfExecption {
		
		IRepositorio<Motorista> listaMotoristas = new RepositorioMotorista();
		IRepositorio<Motorista> lista2;
		IRepositorio<Solicitante> placeholder = new RepositorioSolicitante();
		IRepositorio<Solicitante> placeholde2 = new RepositorioSolicitante();
		IRepositorio<Viagem> lugares = new RepositorioViagem();
		IRepositorio<Viagem> lugares2 = new RepositorioViagem();
		Lugar placeA = new Lugar("Recife", "Derby");
		Lugar placeB = new Lugar("Olinda", "Cé");
		
		Gerente admin = new Gerente("113.544.464-10", "Paulo", "paulo56", "masc");
		Gerente adm;
		
		Motorista driver0 = new Motorista("113.544.464-10", "José", "facil", "masc", "driver@estrada.com");
		//Motorista driver1 = new Motorista("113.544.464-10", "Lucas", "facil", "masc", "driver@estrada.com");
		
		//Solicitante cliente0 = new Solicitante("113.544.464-10", "Bianca", "boa", "fem", "10/06/1985", "teste@legal.com", 345678);
		Solicitante cliente1 = new Solicitante("113.544.464-10", "Mario", "boa", "masc", "10/06/1985", "teste@legal.com", 345678);
		
		//Salvamos motoristas e solicitantes nos seus repositórios
		listaMotoristas.adicionar(driver0);
		//listaMotoristas.adicionar(driver1);
		//placeholder.adicionar(cliente0);
		placeholder.adicionar(cliente1);
		
		//Solicitamos carona 
		cliente1.solicitarCarona(listaMotoristas, lugares, placeA, placeB, 23.69, "dinheiro");
		cliente1.solicitarCarona(listaMotoristas, lugares, placeB, placeA, 23.69, "dinheiro");
		
		//Salvamos todos os envolvidos no banco de dados
		Database.saveStatus(listaMotoristas,"DataBase/Motoristas.txt");
		System.out.println("Base de motoristas ok");
		Database.saveStatus(placeholder, "DataBase/Solicitantes.txt");
		System.out.println("Base de solicitantes ok");
		Database.saveStatus(lugares, "DataBase/Viagens.txt");
		System.out.println("Base de viagens ok");
		Database.saveStatus(admin, "DataBase/Gerente.txt");
		System.out.println("Base de gerente ok");
		
		//Recuperamos todos eles
		lista2 = Database.readDataBase("DataBase/Motoristas.txt");
		placeholde2 = Database.readDataBase("DataBase/Solicitantes.txt");
		lugares2 = Database.readDataBase("DataBase/Viagens.txt");
		adm = Database.readDataBase("DataBase/Gerente.txt");
		

		System.out.println("");
		IRepositorio<Solicitante> lista = new RepositorioSolicitante();
		lista = Database.readDataBase("DataBase/Solicitantes.txt");
        //Database<Tipo>.readDataBase("DataBase/NewUsers.txt");
        lista.buscar(1);
		
		lista2.buscarTodos();
		System.out.println("");
		placeholde2.buscarTodos();
		System.out.println("");
		lugares2.buscarTodos();
		System.out.println(adm.toString());
		System.out.println("\nOutros Detalhes\n");
		
		//Outras operações com os objetos recuperados
		//((Motorista) placeholde2.buscar(1)).historico();
		System.out.println(placeholde2.buscar(1).toString());
		System.out.println(lista2.buscar(1).getViagens());
		//System.out.println(((Motorista) placeholde2.buscar(2)).getViagens());
		
	}
}
