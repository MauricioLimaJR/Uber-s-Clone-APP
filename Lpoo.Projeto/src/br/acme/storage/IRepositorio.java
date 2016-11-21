package br.acme.storage;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public interface IRepositorio<T> {

	public int getQuantiaArray();
	public void adicionar(Solicitante novo) throws RepositorioException;
	public <T> Boolean verificarExistencia(T t) throws RepositorioException;
	public void remover(long id) throws RepositorioException;
	public <T> void alterar(T atual) throws RepositorioException, NullStringException, UnableCpfExecption;	
	public <T> T buscar(long chaveId) throws RepositorioException;	
	public <T> T buscarTodos() throws RepositorioException;
}
