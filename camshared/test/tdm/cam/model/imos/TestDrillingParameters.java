package tdm.cam.model.imos;

import tdm.cam.model.imos.ImosDrilling;


public class TestDrillingParameters {

	private double x = 74;
	private double y = 378;
	private double z = 0;
	private double endX = 380;
	private double endY = 200;
	private double angleX = 0;
	private double angleY = 0;
	private double angleZ = 0;
	private double deep = 11;
	private double diameter = 10;
	private int numDrillings = 1;

	
	public TestDrillingParameters() {
	}

	public TestDrillingParameters diameter(double diameter) {
		this.diameter = diameter;
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
	
	public TestDrillingParameters endX(double endX) {
		this.endX = endX;
		return this;
	}
	
	public TestDrillingParameters endY(double endY) {
		this.endY = endY;
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
	
	public TestDrillingParameters numDrillings(int numDrillings) {
		this.numDrillings = numDrillings;
		return this;
	}
	
	public ImosDrilling create() {
		ImosDrilling d = new ImosDrilling();
		d.setNumDrillings(1);
		d.setX(x);
		d.setY(y);
		d.setZ(z);
		d.setEndX(endX);
		d.setEndY(endY);
		d.setAngleX(angleX);
		d.setAngleY(angleY);
		d.setAngleZ(angleZ);
		d.setDeep(deep);
		d.setDiameter(diameter);
		d.setNumDrillings(numDrillings);
		return d;
	}

}
