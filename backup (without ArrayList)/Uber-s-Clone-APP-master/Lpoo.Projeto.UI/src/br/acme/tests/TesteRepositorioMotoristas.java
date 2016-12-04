package br.acme.tests;

import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.storage.RepositorioMotorista;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class TesteRepositorioMotoristas {

	public static void main(String[] args) throws RepositorioException, NullStringException, UnableCpfExecption, ParseException {
		
		RepositorioMotorista placeholder = new RepositorioMotorista();
		
		Gerente admin = new Gerente("123", "Paulo", "paulo56", "masc");
		
		Motorista driver0 = new Motorista(new Solicitante("321", "José", "facil", "masc", "10/06/1985", "driver@estrada.com", "345678"));
		Motorista driver1 = new Motorista(new Solicitante("321", "José", "facil", "masc", "10/06/1985", "driver@estrada.com", "345678"));
		Motorista driver2 = new Motorista(new Solicitante("321", "José", "facil", "masc", "10/06/1985", "driver@estrada.com", "345678"));
		Motorista driver3 = new Motorista(new Solicitante("321", "José", "facil", "masc", "10/06/1985", "driver@estrada.com", "345678"));
		Motorista driver4 = new Motorista(new Solicitante("321", "José", "facil", "masc", "10/06/1985", "driver@estrada.com", "345678"));
		
		//Utilizamos o gerente para manipular os mostoristas
		
		admin.cadastrarMotorista(driver0, placeholder);
		admin.cadastrarMotorista(driver1, placeholder);		
		admin.cadastrarMotorista(driver2, placeholder);
		
		System.out.println("");
		admin.removerMotorista(driver0, placeholder);
		
		System.out.println("");
		admin.cadastrarMotorista(driver3, placeholder);
		admin.listarMotorista(placeholder);
		
		System.out.println("");
		admin.cadastrarMotorista(driver4, placeholder);
		
		System.out.println("");
		admin.listarMotorista(driver3.getId(), placeholder);
		
		System.out.println("");
		
		
		placeholder.alterar(driver4);
		admin.listarMotorista(driver4.getId(), placeholder); 
		
	}
}
