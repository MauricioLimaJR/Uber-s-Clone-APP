package br.acme.tests;

import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.location.Lugar;
import br.acme.storage.RepositorioMotorista;
import br.acme.storage.RepositorioSolicitante;
import br.acme.storage.RepositorioViagem;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class TesteCaronas {

	public static void main(String[] args) throws ParseException, RepositorioException, NullStringException, UnableCpfExecption {
		
		RepositorioSolicitante placeholder = new RepositorioSolicitante();
		RepositorioMotorista motoristas = new RepositorioMotorista();
		RepositorioViagem lugares = new RepositorioViagem();
		Lugar place = new Lugar("Recife", "Derby");
		Lugar place1 = new Lugar("Olinda", "Cé");
		
		Gerente admin = new Gerente("123", "Paulo", "paulo56", "masc");
		
		Solicitante cliente0 = new Solicitante("5555", "Seu Zé", "boa", "masc", "10/06/1985", "teste@legal.com", 345678);
		Solicitante cliente1 = new Solicitante("6666", "Maria", "boa", "fem", "10/06/1985", "teste@legal.com", 345678);
		Solicitante cliente2 = new Solicitante("7777", "Zanza", "boa", "fem", "10/06/1985", "teste@legal.com", 345678);
		Solicitante cliente3 = new Solicitante("8888", "Seu apressado", "boa", "masc", "10/06/1985", "teste@legal.com", 345678);
		
		Motorista driver0 = new Motorista("321", "José", "facil", "masc", "driver@estrada.com");
		Motorista driver1 = new Motorista("341", "Marcos", "facil", "masc", "driver@estrada.com");
		Motorista driver2 = new Motorista("361", "Sonia", "facil", "fem", "driver@estrada.com");
		Motorista driver3 = new Motorista("821", "Antônio", "facil", "masc", "driver@estrada.com");		
		
		placeholder.adicionar(cliente0);
		placeholder.adicionar(cliente1);
		placeholder.adicionar(cliente2);
		placeholder.adicionar(cliente3);
		
		admin.cadastrarMotorista(driver0, motoristas);
		admin.cadastrarMotorista(driver1, motoristas);
		admin.cadastrarMotorista(driver2, motoristas);
		admin.cadastrarMotorista(driver3, motoristas);
		
		System.out.println("");
		System.out.println(driver0.getDisponivel());
		System.out.println(driver1.getDisponivel());
		System.out.println(driver2.getDisponivel());
		System.out.println(driver3.getDisponivel());
		
		System.out.println("");
		cliente1.solicitarCarona(motoristas, lugares, place, place1, 29.31, "dinheiro");
		System.out.println(driver0.getDisponivel());
				}
}
