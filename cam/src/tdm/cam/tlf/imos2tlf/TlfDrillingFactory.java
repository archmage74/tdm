package tdm.cam.tlf.imos2tlf;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Map;

import tdm.cam.imos.DrillParser;
import tdm.cam.imos.ImosDrilling;
import tdm.cam.math.Dimensions;
import tdm.cam.tlf.Drilling;
import tdm.cam.tlf.RowDrilling;
import tdm.cam.tlf.TlfPart;
import tdm.cam.tlf.imos2tlf.TlfDrillingTemplate;

public class TlfDrillingFactory {

	public static final double THROUGH_ADD_ON = 4.0;

	Map<String, TlfDrillingTemplate> drillings;
	
	public TlfDrillingFactory() {

	}

	public void init() {
		DrillParser parser = new DrillParser();
		drillings = parser.readDrillConfiguration();
	}
	
	public Drilling createDrilling(TlfPart tlfPart, ImosDrilling imosDrilling) {
		if (imosDrilling.getNumDrillings() == 1) {
			return createSingleDrilling(tlfPart.getDimensions(), imosDrilling);
		} else {
			return createRowDrilling(tlfPart.getDimensions(), imosDrilling);
		}
	}

	private Drilling createSingleDrilling(Dimensions dimensions, ImosDrilling imosDrilling) {
		double diameter = imosDrilling.getDiameter();
		Drilling drilling = null;
		
		// 0.01mm epsilon to compare doubles
		if (!imosDrilling.isHorizontal() && imosDrilling.getDeep() > (dimensions.getThick() - 0.01)) {
			drilling = createThroughDrillingForDiameter(diameter, dimensions);
		} else {
			double deep = imosDrilling.getDeep();
			drilling = createDrillingForDiameter(diameter, deep, dimensions);
		}
		fillStandardDrillingValues(drilling, imosDrilling);
		
		return drilling;
	}

	private Drilling createRowDrilling(Dimensions dimensions, ImosDrilling imosDrilling) {
		double diameter = imosDrilling.getDiameter();
		RowDrilling drilling = null;
		
		if (!imosDrilling.isHorizontal() && imosDrilling.getDeep() > (dimensions.getThick() - 0.01)) {
			drilling = createRowThroughDrillingForDiameter(diameter, dimensions);
		} else {
			double deep = imosDrilling.getDeep();
			drilling = createRowDrillingForDiameter(diameter, deep, dimensions);
		}
		fillStandardDrillingValues(drilling, imosDrilling);
		
		drilling.setNumDrillings(imosDrilling.getNumDrillings());
		drilling.setEndX(imosDrilling.getEndX());
		drilling.setEndY(imosDrilling.getEndY());
		
		return drilling;
	}

	private void fillStandardDrillingValues(Drilling drilling, ImosDrilling imosDrilling) {
		drilling.setX(imosDrilling.getX());
		drilling.setY(imosDrilling.getY());
		drilling.setZ(imosDrilling.getZ());
		drilling.setAngleX(imosDrilling.getAngleX());
		drilling.setAngleY(imosDrilling.getAngleY());
		drilling.setAngleZ(imosDrilling.getAngleZ());
	}

	private RowDrilling createRowDrillingForDiameter(double diameter, double deep, Dimensions dimensions) {
		String drillKey = createDrillKey(diameter);
		RowDrilling drilling = createRowDrilling(dimensions, drillKey);
		drilling.setDeep(deep);
		return drilling;
	}

	private RowDrilling createRowThroughDrillingForDiameter(double diameter, Dimensions dimensions) {
		String drillKey = "V" + createDrillKey(diameter);
		RowDrilling drilling = createRowDrilling(dimensions, drillKey);
		setThroughDeepForDrilling(dimensions, drilling);
		return drilling;
	}

	private RowDrilling createRowDrilling(Dimensions dimensions, String drillKey) {
		TlfDrillingTemplate template = drillings.get(drillKey);
		return template.createRowDrilling(dimensions);
	}

	private Drilling createDrillingForDiameter(double diameter, double deep, Dimensions dimensions) {
		String drillKey = createDrillKey(diameter);
		Drilling drilling = createSingleDrilling(dimensions, drillKey);
		drilling.setDeep(deep);
		return drilling;
	}

	private Drilling createThroughDrillingForDiameter(double diameter, Dimensions dimensions) {
		String drillKey = "V" + createDrillKey(diameter);
		Drilling drilling = createSingleDrilling(dimensions, drillKey);
		setThroughDeepForDrilling(dimensions, drilling);
		return drilling;
	}

	private Drilling createSingleDrilling(Dimensions dimensions, String drillKey) {
		TlfDrillingTemplate template = getDrillingTemplate(drillKey);
		return template.createDrilling(dimensions);
	}

	private void setThroughDeepForDrilling(Dimensions dimensions, Drilling drilling) {
		drilling.setDeep(dimensions.getThick() + THROUGH_ADD_ON);
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

}
