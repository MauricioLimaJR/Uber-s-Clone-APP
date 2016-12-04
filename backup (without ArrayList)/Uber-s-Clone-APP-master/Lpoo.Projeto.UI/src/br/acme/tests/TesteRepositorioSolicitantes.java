package br.acme.tests;


import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.storage.RepositorioSolicitante;
import br.acme.users.Gerente;
import br.acme.users.Solicitante;

public class TesteRepositorioSolicitantes {

	public static void main(String[] args) throws ParseException, RepositorioException, NullStringException, UnableCpfExecption {
		RepositorioSolicitante placeholder = new RepositorioSolicitante();
		
		Gerente admin = new Gerente("123", "Paulo", "paulo56", "masc");
		
		Solicitante cliente0 = new Solicitante("5555", "Seu Zé", "boa", "masc", "10/06/1985", "teste@legal.com", "345678");
		Solicitante cliente1 = new Solicitante("5555", "Doca", "boa", "masc", "10/06/1985", "teste@legal.com", "345678");
		Solicitante cliente2 = new Solicitante("6666", "Maria", "boa", "fem", "10/06/1985", "teste@legal.com", "345678");
		Solicitante cliente3 = new Solicitante("7777", "Zanza", "boa", "fem", "10/06/1985", "teste@legal.com", "345678");
		Solicitante cliente4 = new Solicitante("8888", "Seu apressado", "boa", "masc", "10/06/1985", "teste@legal.com", "345678");
		
		placeholder.adicionar(cliente0);
		placeholder.adicionar(cliente1);
		placeholder.adicionar(cliente2);
		System.out.println("");
		placeholder.adicionar(cliente3);
		placeholder.adicionar(cliente4);
		System.out.println("");
		placeholder.alterar(cliente1);
		
		long teste = 9;
		
		placeholder.buscar(teste);        //buscar com ID pelo repositorio
		admin.listarClientes(placeholder);//ou pelo método do gerente
		admin.listarClientes(cliente3.getId(),placeholder);
		
		//placeholder.remover(9);
		
		System.out.println(cliente1.getDataNascimento()); 
		
	}
}
