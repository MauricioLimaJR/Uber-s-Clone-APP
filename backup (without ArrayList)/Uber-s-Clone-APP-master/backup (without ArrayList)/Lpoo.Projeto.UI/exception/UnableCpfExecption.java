package br.acme.exception;

@SuppressWarnings("serial")
public class UnableCpfExecption extends Exception {
	
	public UnableCpfExecption(){
		super("CPF inválido.");
	}
}
