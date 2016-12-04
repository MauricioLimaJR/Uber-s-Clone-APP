package br.acme.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionMaker {

	public Connection getConnection() {
        try {
            return DriverManager.getConnection(
          "jdbc:mysql://localhost/local", "root", "4321");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public Connection getConnectionOnline() {
        try {
            return DriverManager.getConnection(
          "jdbc:mysql://mysql.hostinger.com.br/u828903860_mape", "u828903860_super", "mape1620");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static void main(String[] args) throws SQLException {
		Connection con = new ConnectionMaker().getConnectionOnline();
		System.out.println("Coneção aberta");
        PreparedStatement stmt = con.prepareStatement("select * from users");
        
        ResultSet resul = stmt.executeQuery();
        
        while(resul.next()){
        	System.out.println(resul.getString(2));
        }
        
        con.close();
        stmt.close();
        System.out.println("Conexão fechada");
	}
}
