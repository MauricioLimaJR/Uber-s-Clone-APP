package br.acme.tests;

import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.storage.Database;
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioSolicitante;
import br.acme.users.Solicitante;

public class TesteDbGenerics {

	public static void main(String[] args) throws ParseException, RepositorioException, NullStringException, UnableCpfExecption {
		
		IRepositorio<Solicitante> placeholder = new RepositorioSolicitante();
		IRepositorio<Solicitante> placeholde2 = new RepositorioSolicitante();

		Solicitante cliente0 = new Solicitante("113.544.464-10", "Bianca", "boa", "fem", "10/06/1985", "teste@legal.com", "345678");
		Solicitante cliente1 = new Solicitante("414.022.235-28", "Mario", "boa", "masc", "10/06/1985", "teste@legal.com", "345678");
		
		placeholder.adicionar(cliente0);
		placeholder.adicionar(cliente1);
		
		Database.saveStatus(placeholder, "DataBase/genericTest");
		placeholde2 = Database.readDataBase("DataBase/genericTest");
		
		placeholde2.buscarTodos();
		placeholde2.buscar(1);
		placeholde2.buscar(2);
	}
}