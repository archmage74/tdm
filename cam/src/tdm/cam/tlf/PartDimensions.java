package tdm.cam.tlf;

public class PartDimensions {
	private double length;
	private double width;
	private double thick;

	public PartDimensions() {
	}

	public PartDimensions(double length, double width, double thick) {
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
}