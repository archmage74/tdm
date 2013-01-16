package tdm.cam.imos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tdm.cam.imos.ImosPart;
import tdm.cam.imos.ImosProfile;
import tdm.cam.math.Dimensions;

public class ImosProfileFactory {

	public static final String READ_PROFILES_SQL = "SELECT prfno, prfid, prflen, prfthk, prfp, prfb FROM idbprf WHERE orderid=? AND id=?";

	public PreparedStatement prepareStatement(Connection dbConnection, ImosPart part) throws SQLException {
		PreparedStatement ps = dbConnection.prepareStatement(READ_PROFILES_SQL);
		ps.setString(1, part.getOrderId());
		ps.setString(2, part.getId());
		return ps;
	}

	public ImosProfile createProfile(ResultSet profileResultSet, Dimensions dimensions) throws SQLException {
		ImosProfile profile = new ImosProfile();
		profile.setPrfNo(profileResultSet.getInt("prfno"));
		profile.setPrfId(profileResultSet.getString("prfid"));
		profile.setPrfLen(profileResultSet.getDouble("prflen"));
		profile.setThick(profileResultSet.getDouble("prfthk"));
		profile.setPrfp(profileResultSet.getInt("prfp"));
		profile.setPrfb(profileResultSet.getInt("prfb"));
		return profile;
	}

}

//IDBPRF columns
//private String ID;
//private String ORDERID;
//private int PRFNO;
//private String PRFID;
//private String PRFP;
//private String PRFB;
//private double PRFLEN;
//private double PRFHGT;
//private double PRFTHK;
//private String PRFWS;
//private String PRFNAME;
//private double PRFCPU;
//private String PRFCOL1;
//private String PRFCOL2;
//private String HEIGHT;
//private String CONTLEN;
//private String PRFHGTFIN;
//private String PRFTHKFIN;
//private int DIFFTYPE;
//private String RENDERP;
