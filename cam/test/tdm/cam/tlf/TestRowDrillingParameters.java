package tdm.cam.tlf;

import java.util.Map;

import tdm.cam.db.DrillParser;

public class TestRowDrillingParameters {

	private String drill = "6";
	private double x = 80;
	private double y = 60;
	private double z = 0;
	private double endX = 380;
	private double endY = 200;
	private double angleX = 0;
	private double angleY = 0;
	private double angleZ = 0;
	private double deep = 11;
	private PartDimensions dimensions = new PartDimensions(714, 404, 19);
	
	public static Map<String, DrillingTemplate> drillTemplates;

	
	public TestRowDrillingParameters() {
	}

	public TestRowDrillingParameters drill(String drill) {
		this.drill = drill;
		return this;
	}
	
	public TestRowDrillingParameters dimensions(PartDimensions dimensions) {
		this.dimensions = dimensions;
		return this;
	}
	
	public TestRowDrillingParameters x(double x) {
		this.x = x;
		return this;
	}
	
	public TestRowDrillingParameters y(double y) {
		this.y = y;
		return this;
	}
	
	public TestRowDrillingParameters z(double z) {
		this.z = z;
		return this;
	}
	
	public TestRowDrillingParameters endX(double endX) {
		this.endX = endX;
		return this;
	}
	
	public TestRowDrillingParameters endY(double endY) {
		this.endY = endY;
		return this;
	}
	
	public TestRowDrillingParameters angleX(double angleX) {
		this.angleX = angleX;
		return this;
	}
	
	public TestRowDrillingParameters angleY(double angleY) {
		this.angleY = angleY;
		return this;
	}
	
	public TestRowDrillingParameters angleZ(double angleZ) {
		this.angleZ = angleZ;
		return this;
	}
	
	public TestRowDrillingParameters deep(double deep) {
		this.deep = deep;
		return this;
	}
	
	public RowDrilling create() {
		if (drillTemplates == null) {
			DrillParser parser = new DrillParser();
			drillTemplates = parser.readDrillConfiguration();
		}
		
		DrillingTemplate template = drillTemplates.get(drill);
		RowDrilling d = template.createRowDrilling(dimensions);
		d.setX(x);
		d.setY(y);
		d.setZ(z);
		d.setEndX(endX);
		d.setEndY(endY);
		d.setAngleX(angleX);
		d.setAngleY(angleY);
		d.setAngleZ(angleZ);
		d.setDeep(deep);
		return d;
	}
	
}
