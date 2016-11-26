package br.acme.tests;
import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.storage.RepositorioViagem;
//import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class TesteRepositorioViagem {

	public static void main(String[] args) throws ParseException, RepositorioException, NullStringException, UnableCpfExecption {
		RepositorioViagem placeholder = new RepositorioViagem();
		
		//Gerente admin = new Gerente("123", "Paulo", "paulo56", "masc");
		
		Motorista driver1 = new Motorista("321", "driver@estrada.com", "facil", "José", "masc");
		Motorista driver2 = new Motorista("123", "driver@estrada.com", "facil", "Bianca", "fem");
		
		Solicitante cliente1 = new Solicitante("5555", "Doca", "boa", "masc", "10/06/1985", "teste@legal.com", 345678);
		Solicitante cliente2 = new Solicitante("6666", "Maria", "boa", "fem", "10/06/1985", "teste@legal.com", 345678);
		
		Lugar place1 = new Lugar("Recife", "Av Agm magalhães");
		Lugar place2 = new Lugar("Olinda", "Av Presidente Kennedy");
		double valor=15.65;
		Viagem travel = new Viagem(cliente2, driver1, place1, place2, valor, "dinheiro");
		Viagem travel2 = new Viagem(cliente1, driver2, place2, place1, valor, "cartão de crédito");
		
		placeholder.adicionar(travel);
		placeholder.adicionar(travel2);
		placeholder.buscarTodos();
		placeholder.buscar(travel2.getId());
		
		System.out.println(cliente1.getDataNascimento());

	}
}
