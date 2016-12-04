package br.acme.users;

import java.io.Serializable;

import br.acme.exception.NullStringException;
import br.acme.exception.UnableCpfExecption;

public abstract class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cpf;
	private long id;
	private String nome;
	private String senha;
	private String sexo;
	
	public Usuario(String cpf, String nome, String senha, String sexo) throws NullStringException, UnableCpfExecption{
		setCpf(cpf);
		setNome(nome);
		setSenha(senha);
		setSexo(sexo);
	}
	
    ///////////// GETTERS AND SETTERS /////////////

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws UnableCpfExecption, NullStringException {
		if(cpf==null||cpf.equals(""))
			throw new NullStringException("CPF"); 
		else if(!validaCPF(cpf))
			throw new UnableCpfExecption();
		else 
			this.cpf= cpf;

	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws NullStringException {
		if(nome==null||nome.equals(""))throw new NullStringException("Nome inválido.");
		else this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws NullStringException {
		if(senha==null||senha.equals(""))throw new NullStringException("Senha");
		else this.senha =senha;

	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) throws NullStringException {
		if(sexo==null||sexo.equals(""))throw new NullStringException("Sexo");
		else this.sexo = sexo;

	}
	
	///////////// METHODS /////////////
	
	public String toString(){
		String mensagem="";
		mensagem += "Id = "+this.getId()+" / Nome: "+this.getNome()+"; CPF: "+this.getCpf()+"; Senha: "+this.getSenha()+"; Sexo: "+this.getSexo();
		return mensagem;
	}
	
	//VALIDAÇÕES
	
	boolean validaCPF(String cpf){
		int[] num = new int[11];// 9 e 10 -> verificadores
		int[] sum = {0,0};
		for(int i=0, j=0; i<11; i++, j++){
			if(j==3||j==7||j==11) j++;
			num[i]=Character.getNumericValue(cpf.charAt(j));
		}
		for(int i=0, j=11; i<10; i++, j--){
			if(i!=0)sum[0]+=num[i-1]*j;
			sum[1]+=num[i]*j;
		}
		for(int i=0; i<2; i++){
			sum[i]=(sum[i]%11<2)?0:11-sum[i]%11;
		}
		return (sum[0]==num[9]&&sum[1]==num[10])?true:false;
	}


}
