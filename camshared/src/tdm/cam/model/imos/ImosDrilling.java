package tdm.cam.model.imos;

import javax.xml.bind.annotation.XmlType;

import tdm.cam.math.IDirection;
import tdm.cam.math.Plane;
import tdm.cam.math.PlaneHelper;


@XmlType
public class ImosDrilling implements IDirection {

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

	@Override
	public String toString() {
		return "ImosDrilling [x=" + x + ", y=" + y + ", z=" + z + ", endX=" + endX + ", endY=" + endY + ", angleX=" + angleX + ", angleY=" + angleY
				+ ", angleZ=" + angleZ + ", diameter=" + diameter + ", deep=" + deep + ", numDrillings=" + numDrillings + "]";
	}

	public boolean isHorizontal() {
		Plane plane = PlaneHelper.getInstance().getPlaneForDirection(this);
		if (plane == Plane.FRONT || plane == Plane.BACK || plane == Plane.DIAGONAL) {
			return false;
		} else {
			return true;
		}
	}

}
