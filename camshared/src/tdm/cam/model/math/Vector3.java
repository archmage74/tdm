package tdm.cam.model.math;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Vector3 implements IsSerializable {

	private static final double COMPARE_EPSILON = 0.00001; 
	
	private double x = 0;
	private double y = 0;
	private double z = 0;
	
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
		if (Math.abs(x - other.x) > COMPARE_EPSILON) {
			return false;
		}
		if (Math.abs(y - other.y) > COMPARE_EPSILON) {
			return false;
		}
		if (Math.abs(z - other.z) > COMPARE_EPSILON) {
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
