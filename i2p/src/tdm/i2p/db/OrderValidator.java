package tdm.i2p.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tdm.i2p.db.imos.IMOSProperties;
import tdm.i2p.db.protime.ProtimeProperties;

public class OrderValidator {

	public static boolean validateProtime(String name) throws SQLException {
		if (name == null || name.equalsIgnoreCase("")) {
			return false;
		}

		boolean valid = false;
		Connection con = ConnectionProvider.getConnection(new ProtimeProperties());
		String sql = "select * from  BETRIEBSAUFTRAG where BETRIEBSAUFTRAG_NO = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		
		int count = 0;
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			count++;
		}
		if (count > 0) {
			valid = true;
		}
		
		ConnectionProvider.closeConnection(con);
		return valid;
	}
	
	public static boolean validateImos(String name) throws SQLException {
		if (name == null || name.equalsIgnoreCase("")) {
			return false;
		}

		boolean valid = false;
		Connection con = ConnectionProvider.getConnection(new IMOSProperties());
		String sql = "select * from PROADMIN where NAME = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		
		int count = 0;
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			count++;
		}
		if (count > 0) {
			valid = true;
		}
		
		ConnectionProvider.closeConnection(con);
		return valid;
	}

}
