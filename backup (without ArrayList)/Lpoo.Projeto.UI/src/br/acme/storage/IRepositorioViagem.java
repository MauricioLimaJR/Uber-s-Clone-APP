package br.acme.storage;

import br.acme.exception.RepositorioException;
import br.acme.location.Viagem;

public interface IRepositorioViagem {

	public void adicionar(Viagem nova) throws RepositorioException;
	
	public Boolean verificarExistencia(long id) throws RepositorioException;
	
	public void remover(long id) throws RepositorioException;
	
	public Viagem buscar(long chaveId) throws RepositorioException;

	
	public Viagem[] buscarTodos() throws RepositorioException;
	
}
