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

public class TesteHistorico {
	
	public static void main(String[] args) throws ParseException, RepositorioException, NullStringException, UnableCpfExecption {
		
		RepositorioSolicitante placeholder = new RepositorioSolicitante();
		RepositorioMotorista motoristas = new RepositorioMotorista();
		RepositorioViagem lugares = new RepositorioViagem();
		Lugar place = new Lugar("Recife", "Derby");
		Lugar place1 = new Lugar("Olinda", "Cé");
		
		Gerente admin = new Gerente("123", "Paulo", "paulo56", "masc");
		
		Solicitante cliente0 = new Solicitante("7777", "Zanza", "boa", "fem", "10/06/1985", "teste@legal.com", "345678");
		Solicitante cliente1 = new Solicitante("8888", "Seu apressado", "boa", "masc", "10/06/1985", "teste@legal.com", "345678");
		
		Motorista driver0 = new Motorista(new Solicitante("321", "José", "facil", "masc", "10/06/1985", "driver@estrada.com", "345678"));
		Motorista driver1 = new Motorista(new Solicitante("3421", "José", "facil", "masc", "10/06/1985", "driver@estrada.com", "345678"));
		
		admin.cadastrarMotorista(driver1, motoristas);
		admin.cadastrarMotorista(driver0, motoristas);
		placeholder.adicionar(cliente0);
		placeholder.adicionar(cliente1);
		
		cliente1.solicitarCarona(motoristas, lugares, place, place1, 23.69, "dinheiro");
		cliente1.solicitarCarona(motoristas, lugares, place1, place, 23.69, "dinheiro");
		
		//cliente1.historico();
		driver0.historico();
		driver1.historico();
		//lugares.buscarTodos();
	}
}
