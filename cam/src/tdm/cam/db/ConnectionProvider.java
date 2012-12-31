package tdm.cam.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	public static Connection getConnection(DBProperties dbProperties) throws SQLException {
		Connection connection = null;
		
		try {
			Class.forName(dbProperties.getDriverClass());
			connection = DriverManager.getConnection(
					dbProperties.getUrl(), 
					dbProperties.getUserName(), 
					dbProperties.getPassword());
		} catch (Exception e) {
			throw new RuntimeException("Unable to connect", e);
		}
		
		return connection;
	}
	
	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
