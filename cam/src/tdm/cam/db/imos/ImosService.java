package tdm.cam.db.imos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tdm.cam.db.CamPartFactory;
import tdm.cam.db.DrillingFactory;
import tdm.cam.tlf.CamPart;
import tdm.cam.tlf.Drilling;
import tdm.cam.tlf.DrillingAngleException;

public class ImosService implements IImosService {

	private Connection dbConnection;
	private CamPartFactory camPartFactory;
	private DrillingFactory drillingFactory;

	public ImosService() {
		camPartFactory = new CamPartFactory();
		drillingFactory = new DrillingFactory();
		drillingFactory.init();
	}

	@Override
	public List<CamPart> readParts(String orderId) {
		List<CamPart> parts = readCamParts(orderId);
		for (CamPart part : parts) {
			readDrillings(part);
		}
		return parts;
	}

	private List<CamPart> readCamParts(String orderId) {
		List<CamPart> parts = new ArrayList<CamPart>();
		ResultSet partResultSet = null;
		PreparedStatement ps = null;
		try {
			ps = dbConnection.prepareStatement(camPartFactory.getReadPartSQL());
			ps.setString(1, orderId);

			partResultSet = ps.executeQuery();
			while (partResultSet.next()) {
				CamPart part = camPartFactory.createCamPart(partResultSet);
				parts.add(part);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
				}
			}
			if (partResultSet != null) {
				try {
					partResultSet.close();
					partResultSet = null;
				} catch (SQLException e) {
				}
			}
		}
		return parts;
	}

	private void readDrillings(CamPart camPart) {
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
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
				}
			}
			if (drillResultSet != null) {
				try {
					drillResultSet.close();
					drillResultSet = null;
				} catch (SQLException e) {
				}
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
