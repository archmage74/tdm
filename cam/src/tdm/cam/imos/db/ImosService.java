package tdm.cam.imos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tdm.cam.imos.ImosDrilling;
import tdm.cam.imos.ImosPart;
import tdm.cam.imos.ImosProfile;
import tdm.cam.tlf.DrillingAngleException;

public class ImosService implements IImosService {

	private Connection dbConnection;
	private ImosPartFactory camPartFactory = new ImosPartFactory();
	private ImosDrillingFactory drillingFactory = new ImosDrillingFactory();
	private ImosProfileFactory profileFactory = new ImosProfileFactory();

	public ImosService() {

	}

	@Override
	public List<ImosPart> readParts(String orderId) {
		List<ImosPart> parts = readCamParts(orderId);
		for (ImosPart part : parts) {
			readDrillings(part);
			readProfiles(part);
		}
		return parts;
	}

	private List<ImosPart> readCamParts(String orderId) {
		List<ImosPart> parts = new ArrayList<ImosPart>();
		ResultSet partResultSet = null;
		PreparedStatement ps = null;
		try {
			ps = dbConnection.prepareStatement(camPartFactory.getReadPartSQL());
			ps.setString(1, orderId);

			partResultSet = ps.executeQuery();
			while (partResultSet.next()) {
				ImosPart part = camPartFactory.createCamPart(partResultSet);
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
	private void readDrillings(ImosPart part) {
		ResultSet drillResultSet = null;
		PreparedStatement ps = null;
		try {
			ps = drillingFactory.prepareStatement(dbConnection, part);

			drillResultSet = ps.executeQuery();
			ImosDrilling drilling = null;
			while (drillResultSet.next()) {
				drilling = drillingFactory.createDrilling(drillResultSet, part.getDimensions());
				try {
					part.addDrilling(drilling);
				} catch (DrillingAngleException e) {
					e.setPartBarcode(part.getBarcode());
					throw e;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closePsAndRs(ps, drillResultSet);
		}
	}
	
	private void readProfiles(ImosPart part) {
		PreparedStatement ps = null;
		ResultSet profileResultSet = null;
		try {
			ps = profileFactory.prepareStatement(dbConnection, part);
			profileResultSet = ps.executeQuery();

			while (profileResultSet.next()) {
				ImosProfile profile = profileFactory.createProfile(profileResultSet, part.getDimensions());
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
