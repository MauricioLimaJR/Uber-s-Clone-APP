package br.acme.exception;

@SuppressWarnings("serial")
public class RepositorioException extends Exception{
	public RepositorioException(String str){
		super("Erro: "+str);
	}
}
