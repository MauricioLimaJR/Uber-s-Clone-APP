package br.acme.tests;

import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.UnableCpfExecption;
import br.acme.storage.SolicitanteDAO;
import br.acme.users.Solicitante;

public class TesteDBSQL {

	public static void main(String[] args) throws ParseException, NullStringException, UnableCpfExecption {
		
		//Saving a user into Database MySQL
		Solicitante cliente1 = new Solicitante("119.205.074-69", "Alyson", "boa", "masc", "10/06/1985", "teste@legal.com", "345678");
		
		SolicitanteDAO.insertUser(cliente1);
		
	}
}
