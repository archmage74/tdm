package tdm.cam.model.imos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.google.gwt.user.client.rpc.IsSerializable;

import tdm.cam.model.math.IDirection;


@XmlType(name="drilling")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImosDrilling implements IDirection, IsSerializable {

	protected double x;

	protected double y;
	
	protected double z;
	
	protected double endX;
	
	protected double endY;
	
	protected double angleX;
	
	protected double angleY;
	
	protected double angleZ;

	protected double diameter;

	protected double deep;
	
	protected int numDrillings;

	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(double endY) {
		this.endY = endY;
	}

	public double getAngleX() {
		return angleX;
	}

	public void setAngleX(double angleX) {
		this.angleX = angleX;
	}

	public double getAngleY() {
		return angleY;
	}

	public void setAngleY(double angleY) {
		this.angleY = angleY;
	}

	public double getAngleZ() {
		return angleZ;
	}

	public void setAngleZ(double angleZ) {
		this.angleZ = angleZ;
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public double getDeep() {
		return deep;
	}

	public void setDeep(double deep) {
		this.deep = deep;
	}

	public int getNumDrillings() {
		return numDrillings;
	}

	public void setNumDrillings(int numDrillings) {
		this.numDrillings = numDrillings;
	}

	public ImosDrilling clone() {
		ImosDrilling clone = new ImosDrilling();
		clone.x = x;
		clone.y = y;
		clone.z = z;
		clone.endX = endX;
		clone.endY = endY;
		clone.angleX = angleX;
		clone.angleY = angleY;
		clone.angleZ = angleZ;
		clone.deep = deep;
		clone.diameter = diameter;
		clone.numDrillings = numDrillings;
		return clone;
	}
	
	@Override
	public String toString() {
		return "ImosDrilling [x=" + x + ", y=" + y + ", z=" + z + ", endX=" + endX + ", endY=" + endY + ", angleX=" + angleX + ", angleY=" + angleY
				+ ", angleZ=" + angleZ + ", diameter=" + diameter + ", deep=" + deep + ", numDrillings=" + numDrillings + "]";
	}

}
