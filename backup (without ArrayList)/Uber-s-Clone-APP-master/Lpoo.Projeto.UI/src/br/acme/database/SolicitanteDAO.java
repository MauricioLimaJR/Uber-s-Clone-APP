package br.acme.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import br.acme.exception.NullStringException;
import br.acme.exception.UnableCpfExecption;
import br.acme.users.Solicitante;

public class SolicitanteDAO {

	public static void insertUser(Solicitante user) throws SQLException{
	
		Connection connection = null;
		PreparedStatement stmt = null;
		
	String query = "insert into users"+
			" (name,cpf,birthDay,sex,number,email,password)"+
			"values(?,?,?,?,?,?,?)";
	
	try {
        // prepared statement to insert
		connection = new ConnectionMaker().getConnection();
		//@PrintString
		System.out.println("Coneção aberta");
        stmt = connection.prepareStatement(query);

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
        
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }finally {
    	stmt.close();
        connection.close();
        //@PrintString
      	System.out.println("Coneção encerrada");
	}
	}
	
	public static Solicitante readUser(String email, String pass) throws SQLException {
		String query = "select * from users where email=? AND password=?";
		Solicitante user = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
		// prepared statement to insert
		connection = new ConnectionMaker().getConnection();
		//@PrintString
		System.out.println("Coneção aberta");
        stmt = connection.prepareStatement(query);
        stmt.setString(1, email);
        stmt.setString(2, pass);
        
        ResultSet data = stmt.executeQuery();
        //Get the values
    	
			if(data.next()){ 
			user = new Solicitante(
					data.getLong("id"),
					data.getString("cpf"),
					data.getString("name"),
					data.getString("password"),
					data.getString("sex"),
					data.getDate("birthDay"),
					data.getString("email"),
					data.getString("number")
					);
			}
		} catch (SQLException | ParseException | NullStringException | UnableCpfExecption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			 stmt.close();
	         connection.close();
	         //@PrintString
	       	System.out.println("Coneção encerrada");
		}
       	
       	return user;
	}
	
	public static ArrayList<Solicitante> readUsers() throws SQLException{
		String query = "select * from users";
        ArrayList<Solicitante> list = new ArrayList<Solicitante>();
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
		// prepared statement to insert
		connection = new ConnectionMaker().getConnection();
		//@PrintString
		System.out.println("Coneção aberta");
        stmt = connection.prepareStatement(query);
        
        ResultSet data = stmt.executeQuery();
        
        //Get the values
        while(data.next()){
        	Solicitante user;
			
				user = new Solicitante(
						data.getLong("id"),
						data.getString("cpf"),
						data.getString("name"),
						data.getString("password"),
						data.getString("sex"),
						data.getDate("birthDay"),
						data.getString("email"),
						data.getString("number")
						);
			list.add(user);
		}
        }catch (ParseException | NullStringException | UnableCpfExecption | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			stmt.close();
	        connection.close();
	        //@PrintString
	      	System.out.println("Coneção encerrada");
		}
        	
        return list;
	}
	
	public static Boolean updateUser(Solicitante user) throws SQLException{
		String query = "update users set cpf=?, name=?,"
				+ " password=?, sex=?, birthDay=?,"
				+ " email=?, number=? where id=?";		
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
        try {
        	// prepared statement to insert
    		connection = new ConnectionMaker().getConnection();
    		//@PrintString
    		System.out.println("Coneção aberta");
            stmt = connection.prepareStatement(query);
        	
			stmt.setString(1,user.getNome());
	        stmt.setString(2,user.getCpf());
	        stmt.setDate(3,user.getDataNascimento());
	        stmt.setString(4, user.getSexo());
	        stmt.setString(5, user.getNumeroCelular());
	        stmt.setString(6, user.getEmail());
	        stmt.setString(7, user.getSenha());
	        stmt.setFloat(8, user.getId());
	        
	        stmt.execute();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			stmt.close();
	        connection.close();
	        //@PrintString
	      	System.out.println("Coneção encerrada");
		}
	
      	return true;
	}
	
	public static Boolean deleteUser(Solicitante user) throws SQLException{
		String query = "delete from users where id=?";
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			// prepared statement to insert
			connection = new ConnectionMaker().getConnection();
			//@PrintString
			System.out.println("Coneção aberta");       
			stmt = connection.prepareStatement(query);
			stmt.setLong(1,user.getId());
		        
		    stmt.execute();
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			stmt.close();
	        connection.close();
	        //@PrintString
	      	System.out.println("Coneção encerrada");
		}
       
      	return true;
	}
}
