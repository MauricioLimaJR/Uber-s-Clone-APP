package br.acme.exception;

@SuppressWarnings("serial")
public class NullStringException extends Exception {
	public NullStringException(String str){
		super(str+" não pode ser vazio!");
	}
}
