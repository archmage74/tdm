package tdm.cam.imos.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import tdm.cam.tlf.TlfPart;


public class ImosPartFactory {

	public static final String readPartsSql = "SELECT orderid, id, cnt, name1, barcode, matId, fLeng, fWidth, fThk FROM idbgpl WHERE orderId=?";
	
	ImosDrillingFactory drillingFactory;
	
	public ImosPartFactory() {
		drillingFactory = new ImosDrillingFactory();
	}
	
	public TlfPart createCamPart(ResultSet rs) throws SQLException {
		TlfPart camPart = new TlfPart();
		camPart.setOrderId(rs.getString("orderid"));
		camPart.setId(rs.getString("id"));
		camPart.setName(rs.getString("name1"));
		camPart.setBarcode(rs.getString("barcode"));
		camPart.setMatId(rs.getString("matId"));
		camPart.setLength(rs.getDouble("fLeng"));
		camPart.setWidth(rs.getDouble("fWidth"));
		camPart.setThick(rs.getDouble("fThk"));

		if (rs.getDouble("cnt") > 1.0) {
			throw new RuntimeException("Encountered part with count > 1, dont know how to handle that");
		}
		
		return camPart;
		
	}

	public String getReadPartSQL() {
		return readPartsSql;
	}
	
}
