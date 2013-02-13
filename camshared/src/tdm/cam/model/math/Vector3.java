package tdm.cam.model.math;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.google.gwt.user.client.rpc.IsSerializable;

@XmlType(name="vector3")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vector3 implements IsSerializable {

	public static final Vector3 Z_UNIT_VECTOR = new Vector3(0, 0, 1); 
	
	private double x = 0;
	private double y = 0;
	private double z = 0;
	
	public Vector3() {
		this(0, 0, 0);
	}

	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(Vector3 v) {
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
	}
	
	public Vector3 rotateXDegrees(double angleInDegrees) {
		return rotateX(Math.toRadians(angleInDegrees));
	}
	
	public Vector3 rotateYDegrees(double angleInDegrees) {
		return rotateY(Math.toRadians(angleInDegrees));
	}
	
	public Vector3 rotateZDegrees(double angleInDegrees) {
		return rotateZ(Math.toRadians(angleInDegrees));
	}
	
	public Vector3 rotateX(double angle) {
		double ty = Math.cos(angle) * y + Math.sin(angle) * z; 
		double tz = Math.cos(angle) * z - Math.sin(angle) * y;
		y = ty;
		z = tz;
		return this;
	}

	public Vector3 rotateY(double angle) {
		double tx = Math.cos(angle) * x + Math.sin(angle) * z; 
		double tz = Math.cos(angle) * z - Math.sin(angle) * x;
		x = tx;
		z = tz;
		return this;
	}

	public Vector3 rotateZ(double angle) {
		double tx = Math.cos(angle) * x + Math.sin(angle) * y;
		double ty = Math.cos(angle) * y - Math.sin(angle) * x;
		x = tx;
		y = ty;
		return this;
	}
	
	public double multiply(Vector3 v) {
		return x * v.getX() + y * v.getY() + z * v.getZ(); 
	}

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
	
	public double get(int index) {
		switch (index) {
		case 0:
			return x;
		case 1:
			return y;
		case 2:
			return z;
		default:
			throw new RuntimeException("Vector3 allows index 0, 1 or 2");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Vector3 other = (Vector3) obj;
		if (!Epsilon.equals(x,  other.x)) { 
			return false;
		}
		if (!Epsilon.equals(y,  other.y)) {
			return false;
		}
		if (!Epsilon.equals(z,  other.z)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Vector3 { ");
		sb.append(x).append(", ");
		sb.append(y).append(", ");
		sb.append(z).append(" }");
		return sb.toString();
	}
	
}
