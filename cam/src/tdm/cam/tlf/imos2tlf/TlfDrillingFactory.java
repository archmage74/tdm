package tdm.cam.tlf.imos2tlf;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Map;

import tdm.cam.imos.DrillParser;
import tdm.cam.math.Dimensions;
import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.tlf.TlfDrilling;
import tdm.cam.tlf.TlfRowDrilling;
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
	
	public TlfDrilling createDrilling(TlfPart tlfPart, ImosDrilling imosDrilling) {
		if (imosDrilling.getNumDrillings() == 1) {
			return createSingleDrilling(tlfPart.getDimensions(), imosDrilling);
		} else {
			return createRowDrilling(tlfPart.getDimensions(), imosDrilling);
		}
	}

	private TlfDrilling createSingleDrilling(Dimensions dimensions, ImosDrilling imosDrilling) {
		double diameter = imosDrilling.getDiameter();
		TlfDrilling drilling = null;
		
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

	private TlfDrilling createRowDrilling(Dimensions dimensions, ImosDrilling imosDrilling) {
		double diameter = imosDrilling.getDiameter();
		TlfRowDrilling drilling = null;
		
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

	private void fillStandardDrillingValues(TlfDrilling drilling, ImosDrilling imosDrilling) {
		drilling.setX(imosDrilling.getX());
		drilling.setY(imosDrilling.getY());
		drilling.setZ(imosDrilling.getZ());
		drilling.setAngleX(imosDrilling.getAngleX());
		drilling.setAngleY(imosDrilling.getAngleY());
		drilling.setAngleZ(imosDrilling.getAngleZ());
	}

	private TlfRowDrilling createRowDrillingForDiameter(double diameter, double deep, Dimensions dimensions) {
		String drillKey = createDrillKey(diameter);
		TlfRowDrilling drilling = createRowDrilling(dimensions, drillKey);
		drilling.setDeep(deep);
		return drilling;
	}

	private TlfRowDrilling createRowThroughDrillingForDiameter(double diameter, Dimensions dimensions) {
		String drillKey = "V" + createDrillKey(diameter);
		TlfRowDrilling drilling = createRowDrilling(dimensions, drillKey);
		setThroughDeepForDrilling(dimensions, drilling);
		return drilling;
	}

	private TlfRowDrilling createRowDrilling(Dimensions dimensions, String drillKey) {
		TlfDrillingTemplate template = drillings.get(drillKey);
		return template.createRowDrilling(dimensions);
	}

	private TlfDrilling createDrillingForDiameter(double diameter, double deep, Dimensions dimensions) {
		String drillKey = createDrillKey(diameter);
		TlfDrilling drilling = createSingleDrilling(dimensions, drillKey);
		drilling.setDeep(deep);
		return drilling;
	}

	private TlfDrilling createThroughDrillingForDiameter(double diameter, Dimensions dimensions) {
		String drillKey = "V" + createDrillKey(diameter);
		TlfDrilling drilling = createSingleDrilling(dimensions, drillKey);
		setThroughDeepForDrilling(dimensions, drilling);
		return drilling;
	}

	private TlfDrilling createSingleDrilling(Dimensions dimensions, String drillKey) {
		TlfDrillingTemplate template = getDrillingTemplate(drillKey);
		return template.createDrilling(dimensions);
	}

	private void setThroughDeepForDrilling(Dimensions dimensions, TlfDrilling drilling) {
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
