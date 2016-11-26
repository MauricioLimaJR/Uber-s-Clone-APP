package br.acme.storage;

import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.users.Solicitante;

public interface IRepositorioSolicitante {

	public int getQuantiaArray();
	
	public void adicionar(Solicitante novo) throws RepositorioException;
	
	public Boolean verificarExistencia(String cpf) throws RepositorioException;
	
	public void remover(long id) throws RepositorioException;
	
	public void alterar(Solicitante atual) throws ParseException, RepositorioException, NullStringException, UnableCpfExecption;
		
	public Solicitante buscar(long chaveId) throws RepositorioException;
	
	public Solicitante[] buscarTodos() throws RepositorioException;
	
}
