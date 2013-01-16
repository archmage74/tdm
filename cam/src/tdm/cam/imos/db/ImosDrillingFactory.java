package tdm.cam.imos.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Map;

import tdm.cam.imos.DrillParser;
import tdm.cam.tlf.Drilling;
import tdm.cam.tlf.TlfDrillingTemplate;
import tdm.cam.tlf.PartDimensions;
import tdm.cam.tlf.RowDrilling;

public class ImosDrillingFactory {

	public static final String readDrillingsSql = "SELECT ip_x, ip_y, ip_z, or_x, or_y, or_z, dia, de, ep_x, ep_y, ep_z, cnt FROM idbwg WHERE orderId=? AND id=?";

	Map<String, TlfDrillingTemplate> drillings;
	
	public ImosDrillingFactory() {

	}

	public void init() {
		DrillParser parser = new DrillParser();
		drillings = parser.readDrillConfiguration();
	}
	
	public Drilling createDrilling(ResultSet rs, PartDimensions dimensions) throws SQLException {
		int numDrillings = rs.getInt("cnt");
		if (numDrillings == 1) {
			return createSingleDrilling(rs, dimensions);
		} else {
			return createRowDrilling(rs, dimensions);
		}
	}

	private Drilling createSingleDrilling(ResultSet rs, PartDimensions dimensions) throws SQLException {
		double diameter = rs.getInt("dia");
		double deep = rs.getDouble("de");
		Drilling drilling = createDrillingForDiameter(diameter, dimensions);
		fillStandardDrillingValues(rs, deep, drilling);
		if (drilling.isSideIndependent()) {
			drilling = createThroughDrillingForDiameter(diameter, dimensions);
			fillStandardDrillingValues(rs, deep, drilling);
		}
		return drilling;
	}

	private Drilling createRowDrilling(ResultSet rs, PartDimensions dimensions) throws SQLException {
		double diameter = rs.getInt("dia");
		double deep = rs.getDouble("de");
		RowDrilling drilling = createRowDrillingForDiameter(diameter, dimensions);
		fillStandardDrillingValues(rs, deep, drilling);
		if (drilling.isSideIndependent()) {
			drilling = createRowThroughDrillingForDiameter(diameter, dimensions);
			fillStandardDrillingValues(rs, deep, drilling);
		}
		
		drilling.setNumDrillings(rs.getInt("cnt"));
		drilling.setEndX(rs.getInt("ep_x"));
		drilling.setEndY(rs.getInt("ep_y"));
		
		return drilling;
	}

	private void fillStandardDrillingValues(ResultSet rs, double deep, Drilling drilling) throws SQLException {
		drilling.setX(rs.getDouble("ip_x"));
		drilling.setY(rs.getDouble("ip_y"));
		drilling.setZ(rs.getDouble("ip_z"));
		drilling.setAngleX(rs.getDouble("or_x"));
		drilling.setAngleY(rs.getDouble("or_y"));
		drilling.setAngleZ(rs.getDouble("or_z"));
		if (deep < 0) {
			deep *= -1;
		}
		drilling.setDeep(deep);
	}

	private RowDrilling createRowDrillingForDiameter(double diameter, PartDimensions dimensions) {
		String drillKey = createDrillKey(diameter);
		return createRowDrilling(dimensions, drillKey);
	}

	private RowDrilling createRowThroughDrillingForDiameter(double diameter, PartDimensions dimensions) {
		String drillKey = "V" + createDrillKey(diameter);
		return createRowDrilling(dimensions, drillKey);
	}

	private RowDrilling createRowDrilling(PartDimensions dimensions, String drillKey) {
		TlfDrillingTemplate template = drillings.get(drillKey);
		return template.createRowDrilling(dimensions);
	}

	private Drilling createDrillingForDiameter(Double diameter, PartDimensions dimensions) {
		String drillKey = createDrillKey(diameter);
		return createSingleDrilling(dimensions, drillKey);
	}

	private Drilling createThroughDrillingForDiameter(Double diameter, PartDimensions dimensions) {
		String drillKey = "V" + createDrillKey(diameter);
		return createSingleDrilling(dimensions, drillKey);
	}

	private Drilling createSingleDrilling(PartDimensions dimensions, String drillKey) {
		TlfDrillingTemplate template = getDrillingTemplate(drillKey);
		return template.createDrilling(dimensions);
	}

	private String createDrillKey(Double diameter) {
		String pattern = "0.##";
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(',');
		DecimalFormat format = new DecimalFormat(pattern, symbols);
		String drillKey = format.format(diameter);
		return drillKey;
	}

	private TlfDrillingTemplate getDrillingTemplate(String drillKey) {
		TlfDrillingTemplate template = drillings.get(drillKey);
		if (template == null) {
			throw new RuntimeException("no drill configuration with diameter=" + drillKey);
		}
		return template;
	}

	public String getReadDrillingsSql() {
		return readDrillingsSql;
	}
}
