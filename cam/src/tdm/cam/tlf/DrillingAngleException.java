package tdm.cam.tlf;

public class DrillingAngleException extends RuntimeException {

	private static final long serialVersionUID = -1829209811402254916L;

	private double angleX;
	
	private double angleY;
	
	private double angleZ;
	
	private String partBarcode;

	public DrillingAngleException(double ax, double ay, double az) {
		super();
		this.angleX = ax;
		this.angleY = ay;
		this.angleZ = az;
	}
	
	public void constructMessageFromParameters() {
	}
	
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("encountered unknown angles (%.1f; %.1f; %.1f)", angleX, angleY, angleZ));
		sb.append(" for drilling");
		if (partBarcode != null) {
			sb.append(" on part with barcode=");
			sb.append(partBarcode);
		}
		return sb.toString();
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

	public String getPartBarcode() {
		return partBarcode;
	}

	public void setPartBarcode(String partBarcode) {
		this.partBarcode = partBarcode;
	}
	
}
