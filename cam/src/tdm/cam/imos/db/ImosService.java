package tdm.cam.imos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tdm.cam.tlf.TlfPart;
import tdm.cam.tlf.Drilling;
import tdm.cam.tlf.DrillingAngleException;
import tdm.cam.tlf.TlfProfile;

public class ImosService implements IImosService {

	private Connection dbConnection;
	private ImosPartFactory camPartFactory;
	private ImosDrillingFactory drillingFactory;

	public ImosService() {
		camPartFactory = new ImosPartFactory();
		drillingFactory = new ImosDrillingFactory();
		drillingFactory.init();
	}

	@Override
	public List<TlfPart> readParts(String orderId) {
		List<TlfPart> parts = readCamParts(orderId);
		for (TlfPart part : parts) {
			readDrillings(part);
			readProfiles(part);
			part.optimizeSides();
		}
		return parts;
	}

	private List<TlfPart> readCamParts(String orderId) {
		List<TlfPart> parts = new ArrayList<TlfPart>();
		ResultSet partResultSet = null;
		PreparedStatement ps = null;
		try {
			ps = dbConnection.prepareStatement(camPartFactory.getReadPartSQL());
			ps.setString(1, orderId);

			partResultSet = ps.executeQuery();
			while (partResultSet.next()) {
				TlfPart part = camPartFactory.createCamPart(partResultSet);
				parts.add(part);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closePsAndRs(ps, partResultSet);
		}
		return parts;
	}

	@SuppressWarnings("resource")
	private void readDrillings(TlfPart camPart) {
		ResultSet drillResultSet = null;
		PreparedStatement ps = null;
		try {
			ps = dbConnection.prepareStatement(drillingFactory.getReadDrillingsSql());
			ps.setString(1, camPart.getOrderId());
			ps.setString(2, camPart.getId());

			drillResultSet = ps.executeQuery();
			Drilling drilling = null;
			while (drillResultSet.next()) {
				drilling = drillingFactory.createDrilling(drillResultSet, camPart.getDimensions());
				try {
					camPart.addDrilling(drilling);
				} catch (DrillingAngleException e) {
					e.setPartBarcode(camPart.getBarcode());
					throw e;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closePsAndRs(ps, drillResultSet);
		}
	}
	
	private void readProfiles(TlfPart part) {
		PreparedStatement ps = null;
		ResultSet profileResultSet = null;
		String readProfileSql = "SELECT prfno, prfid, prflen, prfthk, prfp, prfb FROM idbprf WHERE orderid=? AND id=?";
		try {
			ps = dbConnection.prepareStatement(readProfileSql);
			ps.setString(1, part.getOrderId());
			ps.setString(2, part.getId());
			profileResultSet = ps.executeQuery();

			TlfProfile profile = null;
			while (profileResultSet.next()) {
				profile = new TlfProfile();
				profile.setPrfNo(profileResultSet.getInt("prfno"));
				profile.setPrfId(profileResultSet.getString("prfid"));
				profile.setPrfLen(profileResultSet.getDouble("prflen"));
				profile.setThick(profileResultSet.getDouble("prfthk"));
				profile.setPrfp(profileResultSet.getInt("prfp"));
				profile.setPrfb(profileResultSet.getInt("prfb"));
				part.addProfile(profile);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closePsAndRs(ps, profileResultSet);
		}
	}

	private void closePsAndRs(PreparedStatement ps, ResultSet rs) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
	}

	public Connection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

}
