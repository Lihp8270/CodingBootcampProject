package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {
	private Connection conn;
	final private String databaseLocation;
	final private String username;
	final private String password;

	/**
	 * Database connection info stored here so we can quickly change it for systems as required
	 */
	public DatabaseConnector() {
		conn = null;
		databaseLocation = "jdbc:mysql://localhost:3306/theatre";
		username = "theatre_user";
		password = "Pa$$word123";
	}

	public void connect() {
		try {
			conn = DriverManager.getConnection(databaseLocation, username, password);
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			e.printStackTrace();
			return;
		}

		if (conn != null) {
			System.out.println("Connection established.");
		} else {
			System.out.println("Connection null still.");
		}
	}

	public void close() {
		try {
			conn.close();
			System.out.println("Connection closed.");
		} catch (SQLException e) {
			System.out.println("Connection not closed.");
			e.printStackTrace();
		}
	}

	public ResultSet runQuery(PreparedStatement pst) {
		try {
			// pst = conn.prepareStatement(sql,
			// 		ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
            //         ResultSet.CONCUR_UPDATABLE);
			pst.execute();
			
			ResultSet results = pst.getResultSet();
			if (results != null) {
				int rowcount = 0;
				if (results.last()) {
					rowcount = results.getRow();
					results.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first
											// element
				}
				// System.out.println("\n Success.  Result set has " + rowcount + " rows");
			} else {
				// System.out.println("\n Success.  No results returned");
			}
			return results;
		} catch (SQLException e) {
			System.out.println("\n failed to run.");
			e.printStackTrace();
			return null;
		}
	}

	public Connection getConn() {
		return conn;
	}

}