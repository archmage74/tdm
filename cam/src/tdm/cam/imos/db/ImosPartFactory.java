package tdm.cam.imos.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import tdm.cam.math.Dimensions;
import tdm.cam.model.imos.ImosPart;


public class ImosPartFactory {

	public static final String readPartsSql = "SELECT orderid, id, cnt, name1, barcode, matId, fLeng, fWidth, fThk FROM idbgpl WHERE orderId=?";
	
	ImosDrillingFactory drillingFactory;
	
	public ImosPartFactory() {
		drillingFactory = new ImosDrillingFactory();
	}
	
	public ImosPart createCamPart(ResultSet rs) throws SQLException {
		ImosPart camPart = new ImosPart();
		camPart.setOrderId(rs.getString("orderid"));
		camPart.setId(rs.getString("id"));
		camPart.setName(rs.getString("name1"));
		camPart.setBarcode(rs.getString("barcode"));
		camPart.setMatId(rs.getString("matId"));
		camPart.setDimensions(new Dimensions(rs.getDouble("fLeng"), rs.getDouble("fWidth"), rs.getDouble("fThk")));

		if (rs.getDouble("cnt") > 1.0) {
			throw new RuntimeException("Encountered part with count > 1, dont know how to handle that");
		}
		
		return camPart;
		
	}

	public String getReadPartSQL() {
		return readPartsSql;
	}
	
}
