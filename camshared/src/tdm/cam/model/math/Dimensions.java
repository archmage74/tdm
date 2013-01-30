package tdm.cam.model.math;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Dimensions implements Cloneable, IsSerializable {

	private double length;

	private double width;

	private double thick;

	public Dimensions() {

	}

	public Dimensions(double length, double width, double thick) {
		this.length = length;
		this.width = width;
		this.thick = thick;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getThick() {
		return thick;
	}

	public void setThick(double thick) {
		this.thick = thick;
	}

	public Dimensions clone() {
		return new Dimensions(length, width, thick);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(length);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(thick);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(width);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dimensions other = (Dimensions) obj;
		if (other.length == 0 && length != 0)
			return false;
		if (other.width == 0 && width != 0)
			return false;
		if (other.thick == 0 && thick != 0)
			return false;
		if (Math.abs(length / other.length - 1) > 0.0000001)
			return false;
		if (Math.abs(width / other.width - 1) > 0.0000001)
			return false;
		if (Math.abs(thick / other.thick - 1) > 0.0000001)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Dimensions [length=" + length + ", width=" + width + ", thick=" + thick + "]";
	}

}
