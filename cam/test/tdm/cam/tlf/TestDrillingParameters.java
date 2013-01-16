package tdm.cam.tlf;

import java.util.Map;

import tdm.cam.imos.DrillParser;

public class TestDrillingParameters {

	private String drill = "10";
	private double x = 74;
	private double y = 378;
	private double z = 0;
	private double angleX = 0;
	private double angleY = 0;
	private double angleZ = 0;
	private double deep = 11;
	private PartDimensions dimensions = new PartDimensions(714, 404, 19);
	
	public static Map<String, TlfDrillingTemplate> drillTemplates;

	
	public TestDrillingParameters() {
	}

	public TestDrillingParameters drill(String drill) {
		this.drill = drill;
		return this;
	}
	
	public TestDrillingParameters dimensions(PartDimensions dimensions) {
		this.dimensions = dimensions;
		return this;
	}
	
	public TestDrillingParameters x(double x) {
		this.x = x;
		return this;
	}
	
	public TestDrillingParameters y(double y) {
		this.y = y;
		return this;
	}
	
	public TestDrillingParameters z(double z) {
		this.z = z;
		return this;
	}
	
	public TestDrillingParameters angleX(double angleX) {
		this.angleX = angleX;
		return this;
	}
	
	public TestDrillingParameters angleY(double angleY) {
		this.angleY = angleY;
		return this;
	}
	
	public TestDrillingParameters angleZ(double angleZ) {
		this.angleZ = angleZ;
		return this;
	}
	
	public TestDrillingParameters deep(double deep) {
		this.deep = deep;
		return this;
	}
	
	public Drilling create() {
		if (drillTemplates == null) {
			DrillParser parser = new DrillParser();
			drillTemplates = parser.readDrillConfiguration();
		}
		
		TlfDrillingTemplate template = drillTemplates.get(drill);
		Drilling d = template.createDrilling(dimensions);
		d.setX(x);
		d.setY(y);
		d.setZ(z);
		d.setAngleX(angleX);
		d.setAngleY(angleY);
		d.setAngleZ(angleZ);
		d.setDeep(deep);
		return d;
	}
	
}
