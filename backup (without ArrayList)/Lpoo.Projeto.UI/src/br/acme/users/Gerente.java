package br.acme.users;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioMotorista;
import br.acme.storage.RepositorioSolicitante;

public class Gerente extends Usuario {     
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String email;
	
	//Geramos um id para o gerente com um atributo "static"
	private static long idGerente=0;
	
	public Gerente(String cpf, String nome, String senha, String sexo) throws NullStringException, UnableCpfExecption {
		super(cpf, nome, senha, sexo);
		this.setId(++idGerente);
	}

	/////////////  METHODS  /////////////
	
	public void cadastrarMotorista(Motorista novo, IRepositorio<Motorista> motoristas) throws RepositorioException{
        motoristas.adicionar(novo);
	}
	
	public void removerMotorista(Motorista obj, RepositorioMotorista listaMotoristas) throws RepositorioException{
        listaMotoristas.remover(obj);
	}
	
	public void listarMotorista(RepositorioMotorista listaMotoristas) throws RepositorioException{
		listaMotoristas.buscarTodos();
	}
	
	public void listarMotorista(long idToFind, RepositorioMotorista listaMotoristas) throws RepositorioException{
		listaMotoristas.buscar(idToFind);
	}
	
	public void listarClientes(RepositorioSolicitante listaSolicitantes) throws RepositorioException{
		listaSolicitantes.buscarTodos();
	}
     
	public void listarClientes(long idToFind, RepositorioSolicitante listaSolicitantes) throws RepositorioException{
		listaSolicitantes.buscar(idToFind);
	}
	
	public String toString(){
		return super.toString();
	}
	
    // N�o colocamos o m�todo toString para o gerente, pois o mesmo tem atributos limitados aos j� existentes
	// na classe pai, Usuario. Desse modo, implementar o toString aqui seria repeti��o.
	
}
