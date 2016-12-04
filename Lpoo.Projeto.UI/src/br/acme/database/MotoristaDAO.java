package br.acme.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import br.acme.exception.NullStringException;
import br.acme.exception.UnableCpfExecption;
import br.acme.users.Motorista;

public class MotoristaDAO {

	public static void insertDriver(Motorista driver) throws SQLException{
	
		Connection connection = null;
		PreparedStatement stmt = null;
		
	String query = "insert into drivers"+
			" (name,cpf,birthDay,sex,number,email,password)"+
			"values(?,?,?,?,?,?,?)";
	
	try {
        // prepared statement to insert
		connection = new ConnectionMaker().getConnection();
		//@PrintString
		System.out.println("Coneção aberta");
        stmt = connection.prepareStatement(query);

        // Put the values
        stmt.setString(1,driver.getNome());
        stmt.setString(2,driver.getCpf());
        stmt.setDate(3,driver.getDataNascimento());
        stmt.setString(4, driver.getSexo());
        stmt.setString(5, driver.getNumeroCelular());
        stmt.setString(6, driver.getEmail());
        stmt.setString(7, driver.getSenha());

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
	
	public static Motorista readDriver(String email, String pass) throws SQLException {
		String query = "select * from drivers where email=? AND password=?";
		Motorista driver = null;
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
			driver = new Motorista(
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
       	
       	return driver;
	}
	
	public static ArrayList<Motorista> readDrivers() throws SQLException{
		String query = "select * from drivers";
        ArrayList<Motorista> list = new ArrayList<Motorista>();
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
        	Motorista driver;
			
				driver = new Motorista(
						data.getString("cpf"),
						data.getString("name"),
						data.getString("password"),
						data.getString("sex"),
						data.getDate("birthDay"),
						data.getString("email"),
						data.getString("number")
						);
			list.add(driver);
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
	
	public static Boolean updateDriver(Motorista driver) throws SQLException{
		String query = "update drivers set cpf=?, name=?,"
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
        	
			stmt.setString(1,driver.getNome());
	        stmt.setString(2,driver.getCpf());
	        stmt.setDate(3,driver.getDataNascimento());
	        stmt.setString(4, driver.getSexo());
	        stmt.setString(5, driver.getNumeroCelular());
	        stmt.setString(6, driver.getEmail());
	        stmt.setString(7, driver.getSenha());
	        stmt.setFloat(8, driver.getId());
	        
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
	
	public static Boolean deleteUser(Motorista driver) throws SQLException{
		String query = "delete from drivers where id=?";
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			// prepared statement to insert
			connection = new ConnectionMaker().getConnection();
			//@PrintString
			System.out.println("Coneção aberta");       
			stmt = connection.prepareStatement(query);
			stmt.setLong(1,driver.getId());
		        
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
