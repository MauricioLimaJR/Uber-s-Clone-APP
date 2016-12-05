package br.acme.storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class mySQL {

	public mySQL() throws NamingException, SQLException{
	Context context = new InitialContext();
	DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/myDB");

	Connection conn = dataSource.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT ID FROM USERS");

	rs.close();
	stmt.close();
	conn.close();
	
	}
}
