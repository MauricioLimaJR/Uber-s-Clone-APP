package br.acme.tests;

import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.UnableCpfExecption;
import br.acme.users.Solicitante;

public class TesteSolicitanteData {
	public static void main(String[] args) throws ParseException, NullStringException, UnableCpfExecption {
		Solicitante cliente1 = new Solicitante("5555", "Doca", "boa", "masc", "10/06/1985", "teste@legal.com", 345678);
		System.out.println(cliente1.getDataNascimento());
		
	}
}
