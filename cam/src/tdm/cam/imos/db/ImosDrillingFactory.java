package tdm.cam.imos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tdm.cam.math.Dimensions;
import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.imos.ImosPart;

public class ImosDrillingFactory {

	public static final String READ_DRILLINGS_SQL = "SELECT ip_x, ip_y, ip_z, or_x, or_y, or_z, dia, de, ep_x, ep_y, ep_z, cnt FROM idbwg WHERE orderId=? AND id=?";
	public ImosDrillingFactory() {

	}

	public ImosDrilling createDrilling(ResultSet rs, Dimensions dimensions) throws SQLException {
		ImosDrilling drilling = new ImosDrilling();
		drilling.setX(rs.getDouble("ip_x"));
		drilling.setY(rs.getDouble("ip_y"));
		drilling.setZ(rs.getDouble("ip_z"));
		drilling.setEndX(rs.getDouble("ep_x"));
		drilling.setEndY(rs.getDouble("ep_y"));
		drilling.setAngleX(rs.getDouble("or_x"));
		drilling.setAngleY(rs.getDouble("or_y"));
		drilling.setAngleZ(rs.getDouble("or_z"));
		drilling.setDiameter(rs.getInt("dia"));
		double deep = rs.getDouble("de");
		if (deep < 0) {
			deep *= -1;
		}
		drilling.setDeep(deep);
		drilling.setNumDrillings(rs.getInt("cnt"));
		
		return drilling;
	}

	public String getReadDrillingsSql() {
		return READ_DRILLINGS_SQL;
	}

	public PreparedStatement prepareStatement(Connection dbConnection, ImosPart camPart) throws SQLException {
		PreparedStatement ps = dbConnection.prepareStatement(READ_DRILLINGS_SQL);
		ps.setString(1, camPart.getOrderId());
		ps.setString(2, camPart.getId());
		return ps;
	}
}
