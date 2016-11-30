package br.acme.tests;

import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.storage.RepositorioSolicitanteArray;
import br.acme.users.Solicitante;

public class TesteRepositorioArray {
	

	public static void main(String[] args) throws ParseException, RepositorioException, NullStringException, UnableCpfExecption {

		RepositorioSolicitanteArray users = new RepositorioSolicitanteArray();
		Solicitante cliente1 = new Solicitante("113.544.464-10", "Mario", "boa", "masc", "10/06/1985", "teste@legal.com", 345678);
		
		users.adicionar(cliente1);
		users.buscar(cliente1.getId());
		users.buscarTodos();
		users.remover(cliente1);
		users.buscar(cliente1.getId());		

	}
}