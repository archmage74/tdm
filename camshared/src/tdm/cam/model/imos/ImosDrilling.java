package tdm.cam.model.imos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.google.gwt.user.client.rpc.IsSerializable;

import tdm.cam.model.math.IDirection;
import tdm.cam.model.math.Vector3;

@XmlType(name = "drilling")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImosDrilling implements IDirection, IsSerializable {

	protected Vector3 position = new Vector3();

	protected Vector3 endPosition = new Vector3();

	protected Vector3 direction = new Vector3(Vector3.Z_UNIT_VECTOR);

	protected double diameter;

	protected double deep;

	protected int numDrillings;

	public Vector3 getPosition() {
		return new Vector3(position);
	}

	public void setPosition(Vector3 position) {
		this.position = new Vector3(position);
	}

	public Vector3 getEndPosition() {
		return new Vector3(endPosition);
	}

	public void setEndPosition(Vector3 endPosition) {
		this.endPosition = new Vector3(endPosition);
	}

	public Vector3 getDirection() {
		return new Vector3(direction);
	}

	public void setDirection(Vector3 direction) {
		this.direction = new Vector3(direction);
	}

	public double getX() {
		return position.getX();
	}

	public void setX(double x) {
		position.setX(x);
	}

	public double getY() {
		return position.getY();
	}

	public void setY(double y) {
		position.setY(y);
	}

	public double getZ() {
		return position.getZ();
	}

	public void setZ(double z) {
		position.setZ(z);
	}

	public double getEndX() {
		return endPosition.getX();
	}

	public void setEndX(double endX) {
		endPosition.setX(endX);
	}

	public double getEndY() {
		return endPosition.getY();
	}

	public void setEndY(double endY) {
		endPosition.setY(endY);
	}

	public double getEndZ() {
		return endPosition.getZ();
	}

	public void setEndZ(double endZ) {
		endPosition.setZ(endZ);
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
		clone.position = new Vector3(position);
		clone.endPosition = new Vector3(endPosition);
		clone.direction = new Vector3(direction);
		clone.deep = deep;
		clone.diameter = diameter;
		clone.numDrillings = numDrillings;
		return clone;
	}

	@Override
	public String toString() {
		return "ImosDrilling [ position=" + position + ", endPosition=" + endPosition + ", direction=" + direction + ", diameter=" + diameter
				+ ", deep=" + deep + ", numDrillings=" + numDrillings + "]";
	}

}
