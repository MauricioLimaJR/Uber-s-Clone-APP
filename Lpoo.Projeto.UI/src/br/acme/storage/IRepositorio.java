package br.acme.storage;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;

public interface IRepositorio<Tipo> {

	public int getQuantiaArray();
	
	public void adicionar(Tipo obj) throws RepositorioException;
	
	public Boolean verificarExistencia(Tipo obj) throws RepositorioException;
	
	public void remover(Tipo obj) throws RepositorioException;
	
	public void alterar(Tipo obj) throws RepositorioException, NullStringException, UnableCpfExecption;	
	
	public Tipo buscar(long id) throws RepositorioException;	
	
	public Tipo[] buscarTodos() throws RepositorioException;
}
