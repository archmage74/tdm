package tdm.i2p.db.protime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import tdm.i2p.db.imos.IMOSPart;

public class ProtimeService {
	Connection dbConnection;

	int lfdNo = 1;
	HashMap<String, Material> materials = new HashMap<String, Material>();
	
	public Collection<ProtimePart> convert(String orderId, int hPos, Collection<IMOSPart> imosParts, Collection<String> materials) {
		List<ProtimePart> protimePartList = new ArrayList<ProtimePart>();
		
		ProtimePart protimePart;
		for (IMOSPart imosPart : imosParts) {
			if (materials.contains(imosPart.getMatId())) {
				protimePart = new ProtimePart();
				protimePart.setManualParams(orderId, hPos);
				protimePart.initFromIMOSPart(imosPart);
				protimePart.LFD_NO = lfdNo++;
				protimePartList.add(protimePart);
			}
		}
		
		return protimePartList;
	}
	
	public void preprocessParts(Collection<ProtimePart> parts) throws SQLException {
		for (ProtimePart part : parts) {
			preprocessPart(part);
		}
	}
	
	public void preprocessPart(ProtimePart part) throws SQLException {
		Material mat = retrieveMaterial(part.MATERIAL);
		part.STAERKE = mat.thickness;
		
		if (part.KANTE_V != null) {
			mat = retrieveMaterial(part.KANTE_V);
			part.KANTE_V_ST = mat.thickness;
			part.KANTE_V_VN = mat.edgeVN;
			part.KANTE_V_B = part.STAERKE; 
		}
		
		if (part.KANTE_R != null) {
			mat = retrieveMaterial(part.KANTE_R);
			part.KANTE_R_ST = mat.thickness;
			part.KANTE_R_VN = mat.edgeVN;
			part.KANTE_R_B = part.STAERKE; 
		}
		
		if (part.KANTE_H != null) {
			mat = retrieveMaterial(part.KANTE_H);
			part.KANTE_H_ST = mat.thickness;
			part.KANTE_H_VN = mat.edgeVN;
			part.KANTE_H_B = part.STAERKE; 
		}
		
		if (part.KANTE_L != null) {
			mat = retrieveMaterial(part.KANTE_L);
			part.KANTE_L_ST = mat.thickness;
			part.KANTE_L_VN = mat.edgeVN;
			part.KANTE_L_B = part.STAERKE; 
		}
	}
	
	public Material retrieveMaterial(String id) throws SQLException {
		if (id == null) {
			System.out.println("mat id is null");
			return null;
		}
		Material mat = materials.get(id);
		if (mat == null) {
			String readMaterial = "select ARTIKEL_NO, MATERIALSTAERKE, KANTE_VN from ARTIKEL where ARTIKEL_NO=?";
			PreparedStatement ps = dbConnection.prepareStatement(readMaterial);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String thickness = rs.getString("MATERIALSTAERKE");
				String edgeVN = rs.getString("KANTE_VN");
				mat = new Material(id, thickness, edgeVN);
			} else {
				throw new RuntimeException("Unbekanntes Material: " + id);
			}
			rs.close();
			ps.close();
		}
		return mat;
	}
	
	public void writePart(ProtimePart part) throws SQLException {
		String writePartSql = "insert into TEILE_SLGEL ( PROJEKT_NO, HPOS, SPOS, LFD_NO, LFD_NO_PROJ_POS, STAERKE, BEZEICHNUNG, BESCHREIBUNG, MATERIAL, LAENGE, BREITE, BELAG_A, BELAG_I, KANTE_V, KANTE_V_ST, KANTE_V_VN, KANTE_V_B, KANTE_V_L, KANTE_H, KANTE_H_ST, KANTE_H_VN, KANTE_H_B, KANTE_H_L, KANTE_L, KANTE_L_ST, KANTE_L_VN, KANTE_L_B, KANTE_L_L, KANTE_R, KANTE_R_ST, KANTE_R_VN, KANTE_R_B, KANTE_R_L, LO, MO, RO, MR, RU, MU, LU, ML, MENGE, KR_SAUM, KL_SAUM, KH_SAUM, KV_SAUM, BK_NO ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		PreparedStatement ps = dbConnection.prepareStatement(writePartSql);
		
		int idx = 1;
		ps.setString(idx++, part.PROJEKT_NO);
		ps.setInt(idx++, part.HPOS);
		ps.setInt(idx++, part.SPOS);
		ps.setInt(idx++, part.LFD_NO);
		ps.setInt(idx++, part.LFD_NO_PROJ_POS);
		ps.setString(idx++, part.STAERKE);
		ps.setString(idx++, part.BEZEICHNUNG);
		ps.setString(idx++, part.BESCHREIBUNG);
		ps.setString(idx++, part.MATERIAL);
		ps.setString(idx++, part.LAENGE);
		ps.setString(idx++, part.BREITE);

		if (part.BELAG_A != null) {
			ps.setString(idx++, part.BELAG_A);
		} else {
			ps.setNull(idx++, Types.CHAR);
		}
		
		if (part.BELAG_I != null) {
			ps.setString(idx++, part.BELAG_I);
		} else {
			ps.setNull(idx++, Types.CHAR);
		}

		if (part.KANTE_V != null) {
			ps.setString(idx++, part.KANTE_V);
			ps.setString(idx++, part.KANTE_V_ST);
			ps.setString(idx++, part.KANTE_V_VN);
			ps.setString(idx++, part.KANTE_V_B);
			ps.setString(idx++, part.KANTE_V_L);
		} else {
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
		}

		if (part.KANTE_H != null) {
			ps.setString(idx++, part.KANTE_H);
			ps.setString(idx++, part.KANTE_H_ST);
			ps.setString(idx++, part.KANTE_H_VN);
			ps.setString(idx++, part.KANTE_H_B);
			ps.setString(idx++, part.KANTE_H_L);
		} else {
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
		}

		if (part.KANTE_L != null) {
			ps.setString(idx++, part.KANTE_L);
			ps.setString(idx++, part.KANTE_L_ST);
			ps.setString(idx++, part.KANTE_L_VN);
			ps.setString(idx++, part.KANTE_L_B);
			ps.setString(idx++, part.KANTE_L_L);
		} else {
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
		}

		if (part.KANTE_R != null) {
			ps.setString(idx++, part.KANTE_R);
			ps.setString(idx++, part.KANTE_R_ST);
			ps.setString(idx++, part.KANTE_R_VN);
			ps.setString(idx++, part.KANTE_R_B);
			ps.setString(idx++, part.KANTE_R_L);
		} else {
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
			ps.setNull(idx++, Types.CHAR);
		}

		ps.setInt(idx++, part.LO);
		ps.setInt(idx++, part.MO);
		ps.setInt(idx++, part.RO);
		ps.setInt(idx++, part.MR);
		ps.setInt(idx++, part.RU);
		ps.setInt(idx++, part.MU);
		ps.setInt(idx++, part.LU);
		ps.setInt(idx++, part.ML);
		ps.setString(idx++, part.MENGE);
		ps.setInt(idx++, part.KR_SAUM);
		ps.setInt(idx++, part.KL_SAUM);
		ps.setInt(idx++, part.KH_SAUM);
		ps.setInt(idx++, part.KV_SAUM);
		ps.setString(idx++, part.BK_NO);
		
		ps.execute();
		ps.close();
	}
	
//		insert into PROJ_POS_BESCHR (PROJEKT_NO, HPOS, SPOS, BESCHREIBUNG) VALUES ('TEST_10', 888, 888, 'POSITION888');

	public void writePosDescription(String orderId, int hPos, Collection<SPosEntry> sPosEntries) throws SQLException {
		String sql = "insert into PROJ_POS_BESCHR (PROJEKT_NO, HPOS, SPOS, BESCHREIBUNG) VALUES (?,?,?,?)";
		PreparedStatement ps = dbConnection.prepareStatement(sql);
		for (SPosEntry sPosEntry : sPosEntries) {
			ps.setString(1, orderId);
			ps.setInt(2, hPos);
			ps.setInt(3, sPosEntry.getSPos());
			ps.setString(4, sPosEntry.getDescription());
			ps.execute();
		}
		ps.close();
	}

	public void writePartDescription(ProtimePart part) throws SQLException {
		String sql = "insert into PTTECH_POS (PROJEKT_NO, HPOS, SPOS, LFD_NO, MENGE, BK_NO) VALUES (?,?,?,?,?,?)";
		// insert into PTTECH_POS (PROJEKT_NO, HPOS, SPOS, LFD_NO, MENGE, BK_NO) VALUES ('TEST_10', 888, 888, 888, 1, 'LEER')
		PreparedStatement ps = dbConnection.prepareStatement(sql);
        try {     
                ps.setString(1, part.PROJEKT_NO);
                ps.setInt(2, part.HPOS);
                ps.setInt(3, part.SPOS);
                ps.setInt(4, part.LFD_NO_PROJ_POS);
                ps.setString(5, part.MENGE);
                ps.setString(6, part.BK_NO);
                ps.execute();
         } catch (SQLException e) {
          // row exists already, nothing should do
         } finally {
                ps.close();
         }
    }
			
	public Connection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
}
