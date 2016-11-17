package br.acme.storage;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.users.Motorista;

public interface IRepositorioMotorista{

	public int getQuantiaArray();
		
	public void adicionar(Motorista novo) throws RepositorioException;
	
	public Boolean verificarExistencia(String cpf) throws RepositorioException;
	
	public void remover(long id) throws RepositorioException;
	
	public void alterar(Motorista atual) throws RepositorioException, NullStringException, UnableCpfExecption;		
		
	public Motorista buscar(long chaveId) throws RepositorioException;
	
	public Motorista[] buscarTodos() throws RepositorioException;
	
}
