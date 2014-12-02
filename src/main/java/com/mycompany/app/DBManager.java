package com.mycompany.app;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	private static final String URL = "jdbc:mysql://localhost/test";

	public void executeTestSelect() {
		// Create the connection using the static getConnection method
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, "root", "root");
			// Create a Statement class to execute the SQL statement
			Statement stmt = con.createStatement();
			// Execute the SQL statement and get the results in a Resultset
			int id = 7;			
			ResultSet rs = stmt.executeQuery(String.format("SELECT c.name, (SELECT COUNT(*) from product p where p.country=c.country ) prod_for_client, (SELECT COUNT(*) from product p2 ) all_products FROM client c where c.id = %s ;", id));
			while (rs.next()) {
				System.out.print("Name: " + rs.getString("name") + " | ");
				System.out.print("Number of products : "  + rs.getString("prod_for_client") + " | ");
				System.out.println("Total number of products: " + rs.getString("all_products") + " | ");				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Close the connection
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
