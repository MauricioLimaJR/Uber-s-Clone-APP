package br.acme.storage;

import java.sql.Connection;
import java.sql.DriverManager;
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
	
}
