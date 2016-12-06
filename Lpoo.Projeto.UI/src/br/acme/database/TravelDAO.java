package br.acme.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.acme.location.Lugar;
import br.acme.location.Viagem;

public class TravelDAO {

	public static void insertTravel(Viagem travel) throws SQLException{
	
		Connection connection = null;
		PreparedStatement stmt = null;
		
	String query = "insert into travels"+
			" (UserID, DriverID, sourceIDF, sourceAD, destinyIDF, destinyAD, value, payForm)"+
			"values(?,?,?,?,?,?,?,?)";
	
	try {
        // prepared statement to insert
		connection = new ConnectionMaker().getConnection();
		//@PrintString
		System.out.println("Coneção aberta");
        stmt = connection.prepareStatement(query);

        // Put the values
        stmt.setLong(1, travel.getClienteId());
        stmt.setLong(2, travel.getMotoristaId());
        stmt.setString(3, travel.getOrigem().getIdentificadorLugar());
        stmt.setString(4, travel.getOrigem().getEndereco());
        stmt.setString(5, travel.getDestino().getIdentificadorLugar());
        stmt.setString(6, travel.getDestino().getEndereco());
        stmt.setDouble(7, travel.getValorViagem());
        stmt.setString(8, travel.getFormaPagamento());

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
	
	public static ArrayList<Viagem> readTravel(long id) throws SQLException {
		String query = "select * from travels where UserID=? OR DriverID=?";
		ArrayList<Viagem> travels = new ArrayList<Viagem>();
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
		// prepared statement to insert
		connection = new ConnectionMaker().getConnection();
		//@PrintString
		System.out.println("Coneção aberta");
        stmt = connection.prepareStatement(query);
        stmt.setLong(1, id);
        stmt.setLong(2, id);
        
        ResultSet data = stmt.executeQuery();
        //Get the values
    	
			if(data.next()){ 
				Lugar source = new Lugar(data.getString("sourceIDF"),data.getString("sourceAD"));
				Lugar destiny = new Lugar(data.getString("destinyIDF"), data.getString("destinyAD"));
				Viagem travel = new Viagem(
						data.getLong("UserID"),
						data.getLong("DriverID"),
						source,
						destiny,
						data.getDouble("value"),
						data.getString("payForm")
						);
				travels.add(travel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			 stmt.close();
	         connection.close();
	         //@PrintString
	       	System.out.println("Coneção encerrada");
		}
       	
       	return travels;
	}
	
	public static ArrayList<Viagem> readTravels() throws SQLException{
		String query = "select * from travels";
        ArrayList<Viagem> list = new ArrayList<Viagem>();
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
        	Lugar source = new Lugar(data.getString("sourceIDF"),data.getString("sourceAD"));
			Lugar destiny = new Lugar(data.getString("destinyIDF"), data.getString("destinyAD"));
			Viagem travel = new Viagem(
					data.getLong("UserID"),
					data.getLong("DriverID"),
					source,
					destiny,
					data.getDouble("value"),
					data.getString("payForm")
					);
			travel.setId(data.getLong("id"));
	
			list.add(travel);
		}
        }catch (SQLException e) {
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
}