package br.acme.tests;

import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.storage.RepositorioMotorista;
import br.acme.storage.RepositorioSolicitante;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class testeToString {

	public static void main(String[] args) throws ParseException, RepositorioException, NullStringException, UnableCpfExecption {

		RepositorioSolicitante placeholder = new RepositorioSolicitante();
		RepositorioMotorista motoristas = new RepositorioMotorista();
		
		Gerente admin = new Gerente("123", "Paulo", "paulo56", "masc");
		Gerente admin2 = new Gerente("423", "Paulo", "paulo56", "masc");
		
		Solicitante cliente0 = new Solicitante("7777", "Zanza", "boa", "fem", "10/06/1985", "teste@legal.com", "345678");
		Solicitante cliente1 = new Solicitante("8888", "Seu apressado", "boa", "masc", "10/06/1985", "teste@legal.com", "345678");
		
		Motorista driver0 = new Motorista(new Solicitante("321", "José", "facil", "masc", "10/06/1985", "driver@estrada.com", "345678"));
		Motorista driver1 = new Motorista(new Solicitante("321", "José", "facil", "masc", "10/06/1985", "driver@estrada.com", "345678"));;
		
		placeholder.adicionar(cliente0);
		placeholder.adicionar(cliente1);
		
		motoristas.adicionar(driver0);
		motoristas.adicionar(driver1);
		
		admin.cadastrarMotorista(driver0, motoristas);
		admin.cadastrarMotorista(driver1, motoristas);
		
		System.out.println(admin.toString());
		System.out.println(admin2.toString());
		System.out.println(cliente0.toString());
		System.out.println(driver0.toString());
		
		System.out.println(cliente0.getId());

	}

}
