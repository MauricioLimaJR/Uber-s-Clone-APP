package br.acme.storage;

import br.acme.location.Viagem;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class Repositorio {

	public static IRepositorio<Solicitante> rpSolicitantes = new RepositorioSolicitante();
	public static IRepositorio<Motorista> rpMotoristas = new RepositorioMotorista();
	public static RepositorioViagem rpViagens = new RepositorioViagem();
}
