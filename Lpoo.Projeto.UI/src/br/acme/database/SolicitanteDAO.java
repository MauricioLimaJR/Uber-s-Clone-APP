package br.acme.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.acme.users.Solicitante;

public class SolicitanteDAO {

	public static void insertUser(Solicitante user){
	
	String query = "insert into users"+
			" (name,cpf,birthDay,sex,number,email,password)"+
			"values(?,?,?,?,?,?,?)";
	
	try {
        // prepared statement to insert
		Connection connection = new ConnectionMaker().getConnection();
		//@PrintString
		System.out.println("Coneção aberta");
        PreparedStatement stmt = connection.prepareStatement(query);

        // Put the values
        stmt.setString(1,user.getNome());
        stmt.setString(2,user.getCpf());
        stmt.setDate(3,user.getDataNascimento());
        stmt.setString(4, user.getSexo());
        stmt.setString(5, user.getNumeroCelular());
        stmt.setString(6, user.getEmail());
        stmt.setString(7, user.getSenha());

        // execute 
        stmt.execute();
        stmt.close();
        //@PrintString
      	System.out.println("Coneção encerrada");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
	}
}
