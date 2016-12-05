package br.acme.storage;

import java.util.ArrayList;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;

public interface IRepositorio<Tipo> {

	public int getQuantiaArray();
	
	public void adicionar(Tipo obj) throws RepositorioException;
	
	public Boolean verificarExistencia(Tipo obj) throws RepositorioException;
	
	public Boolean remover(Tipo obj) throws RepositorioException;
	
	public void alterar(Tipo obj) throws RepositorioException, NullStringException, UnableCpfExecption;	
	
	public Tipo buscar(long id) throws RepositorioException;	
	
	public ArrayList<Tipo> buscarTodos() throws RepositorioException;
}
