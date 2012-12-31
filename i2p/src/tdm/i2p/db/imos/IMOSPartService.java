package tdm.i2p.db.imos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import tdm.i2p.db.protime.SPosEntry;

public class IMOSPartService {
	
	private Connection dbConnection = null;
	
	private int sPosCounter = 1;
	private HashMap<String, SPosEntry> sPosMap = new HashMap<String, SPosEntry>();
	
	private ArrayList<IMOSPart> failureParts = new ArrayList<IMOSPart>();
	
	public Connection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public Collection<IMOSPart> readParts(String orderName) throws SQLException {
		String readPartsSql = "SELECT orderid, id, cnt, name1, barcode, matId, fLeng, fWidth, surfTId, surfBId FROM idbgpl WHERE orderId=?"; 
		PreparedStatement ps = dbConnection.prepareStatement(readPartsSql);
		ps.setString(1, orderName);
		
		ResultSet parts = ps.executeQuery();
		
		List<IMOSPart> partList = new ArrayList<IMOSPart>();
		IMOSPart part = null;
		while (parts.next()) {
			part = createFromRow(parts);
			partList.add(part);
		}
		parts.close();
		
		for (IMOSPart part2 : partList) {
			readProfiles(part2);
		}
		
		return partList;
	}

	public void validateParts(Collection<IMOSPart> parts) {
		validateByMaterial(parts);
		validateByProfileNumber(parts);
	}
	
	public void validateByMaterial(Collection<IMOSPart> parts) {
		ArrayList<IMOSPart> fail = new ArrayList<IMOSPart>(); 
		for (IMOSPart part : parts) {
			String material = part.getMatId();
			if (material == null || "".equals(material)) {
				fail.add(part);
			}
		}
		failureParts.addAll(fail);
		parts.removeAll(fail);
	}
	
	public void validateByProfileNumber(Collection<IMOSPart> parts) {
		ArrayList<IMOSPart> fail = new ArrayList<IMOSPart>(); 
		for (IMOSPart part : parts) {
			int profileNum = part.getProfileMap().size();
			if (profileNum < 4) {
				fail.add(part);
			}
		}
		failureParts.addAll(fail);
		parts.removeAll(fail);
	}
	
	private void readProfiles(IMOSPart part) throws SQLException {
		String readProfileSql = "SELECT prfno, prfid, prflen, prfp, prfb FROM idbprf WHERE orderid=? AND id=?";
		PreparedStatement ps = dbConnection.prepareStatement(readProfileSql);
		ps.setString(1, part.getOrderId());
		ps.setString(2, part.getId());
		ResultSet profiles = ps.executeQuery();
		
		IMOSPartProfile profile = null;
		while (profiles.next()) {
			profile = new IMOSPartProfile();
			profile.setPrfNo(profiles.getInt("prfno"));
			profile.setPrfId(profiles.getString("prfid"));
			profile.setPrfLen(profiles.getDouble("prflen"));
			profile.setPrfp(profiles.getInt("prfp"));
			profile.setPrfb(profiles.getInt("prfb"));
			part.putProfile(profile);
		}
	}
	
	private IMOSPart createFromRow(ResultSet rs) throws SQLException {
		IMOSPart part = new IMOSPart();
		part.setOrderId(rs.getString(1));
		part.setId(rs.getString(2));
		part.setCnt(rs.getDouble(3));
		part.setName1(rs.getString(4));
		part.setBarcode(rs.getString(5));
		part.setMatId(rs.getString(6));
		part.setFLeng(rs.getDouble(7));
		part.setFWidth(rs.getDouble(8));
		part.setSurfTId(rs.getString(9));
		part.setSurfBId(rs.getString(10));
		return part;
	}
	
	public void assignSPos(String orderId, Collection<IMOSPart> imosParts) throws SQLException {
		String readGprs = "SELECT highartid, cpid, width, height, depth FROM IDBGRPS as g, IDBINFO as i WHERE g.orderId=? AND i.ORDERID=? and g.ID=? and g.HIGHARTID=i.ID";
		PreparedStatement ps = dbConnection.prepareStatement(readGprs);
		for (IMOSPart part : imosParts) {
			ps.setString(1, orderId);
			ps.setString(2, orderId);
			ps.setString(3, part.getId());
			ResultSet rs = ps.executeQuery();
			SPosEntry sPosEntry = null;
			if (!rs.next()) {
				sPosEntry = generateSPosEntry(null, 0, 0, 0);
			} else {
				String artId = rs.getString("highartid");
				String cpid = rs.getString("cpid");
				float w = rs.getFloat("width");
				float h = rs.getFloat("height");
				float d = rs.getFloat("depth");
	
	
				if ("0".equals(artId)) {
					throw new RuntimeException("artId == 0"); 
				} else if (sPosMap.containsKey(artId)) {
					sPosEntry = sPosMap.get(artId);
				} else {
					sPosEntry = generateSPosEntry(cpid, w, h, d);
					sPosMap.put(artId, sPosEntry);
				}
			}
			part.setSPos(sPosEntry);
			rs.close();
		}
		ps.close();
		System.out.println("assignSPos ended");
	}
	
	private SPosEntry generateSPosEntry(String cpid, float w, float h, float d) {
		Integer sPos = new Integer(sPosCounter++);
		DecimalFormat df = new DecimalFormat("#");
		String description = null;

		if (cpid == null || "".equals(cpid)) {
			description = "Einfachbauteil";
		} else {
			description = 
				cpid + " " + 
				df.format(w) + "x" + 
				df.format(h) + "x" +
				df.format(d);
		}
		return new SPosEntry(sPos, description);
	}

	public int getSPosCounter() {
		return sPosCounter;
	}

	public HashMap<String, SPosEntry> getSPosMap() {
		return sPosMap;
	}

	public void setSPosMap(HashMap<String, SPosEntry> sPosMap) {
		this.sPosMap = sPosMap;
	}

	public ArrayList<IMOSPart> getFailureParts() {
		return failureParts;
	}

	public void setFailureParts(ArrayList<IMOSPart> failureParts) {
		this.failureParts = failureParts;
	}
}
