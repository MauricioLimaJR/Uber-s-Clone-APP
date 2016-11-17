package br.acme.tests;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.storage.RepositorioMotorista;
import br.acme.users.Gerente;
import br.acme.users.Motorista;

public class TesteRepositorioMotoristas {

	public static void main(String[] args) throws RepositorioException, NullStringException, UnableCpfExecption {
		
		RepositorioMotorista placeholder = new RepositorioMotorista();
		
		Gerente admin = new Gerente("123", "Paulo", "paulo56", "masc");
		
		Motorista driver0 = new Motorista("321", "José", "facil", "masc", "driver@estrada.com");
		Motorista driver1 = new Motorista("321", "Lucas", "facil", "masc", "driver@estrada.com");
		Motorista driver2 = new Motorista("341", "Marcos", "facil", "masc", "driver@estrada.com");
		Motorista driver3 = new Motorista("361", "Sonia", "facil", "fem", "driver@estrada.com");
		Motorista driver4 = new Motorista("821", "Antônio", "facil", "masc", "driver@estrada.com");
		
		//Utilizamos o gerente para manipular os mostoristas
		
		admin.cadastrarMotorista(driver0, placeholder);
		admin.cadastrarMotorista(driver1, placeholder);		
		admin.cadastrarMotorista(driver2, placeholder);
		
		System.out.println("");
		admin.removerMotorista(driver0.getId(), placeholder);
		
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
