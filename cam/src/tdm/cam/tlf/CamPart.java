package tdm.cam.tlf;

import java.util.ArrayList;
import java.util.List;

public class CamPart implements ITlfNode {

	private String id;

	private String orderId;

	private String name;

	private String matId;

	private String barcode;

	private PartDimensions dimensions = new PartDimensions();

	private CamPartSide frontSide = new FrontPartSide(dimensions);

	private CamPartSide backSide = new BackPartSide(dimensions);

	public CamPart() {

	}

	public void moveThroughDrillingsToFrontside() {
		// FIXME better rule: move all to one side (prefer inner side) if possible
		List<Drilling> toMove = new ArrayList<Drilling>();
		for (Drilling drilling : backSide.getDrillings()) {
			if (drilling.isThrough()) {
				toMove.add(drilling);
			}
		}
		for (Drilling drilling : toMove) {
			if (!backSide.removeDrilling(drilling)) {
				throw new RuntimeException("Could not move drilling, could not find it anymore :-(");
			}
			frontSide.addDrilling(drilling);
		}
	}

	public void moveHorizontalToBackside() {
		List<Drilling> toMove = new ArrayList<Drilling>();
		toMove.clear();
		for (Drilling drilling : frontSide.getPlane1Drillings()) {
			toMove.add(drilling);
		}
		for (Drilling drilling : toMove) {
			if (!frontSide.removePlane1Drilling(drilling)) {
				throw new RuntimeException("Could not move drilling, could not find it anymore :-(");
			}
			backSide.addPlane1Drilling(drilling);
		}
		toMove.clear();
		for (Drilling drilling : frontSide.getPlane2Drillings()) {
			toMove.add(drilling);
		}
		for (Drilling drilling : toMove) {
			if (!frontSide.removePlane2Drilling(drilling)) {
				throw new RuntimeException("Could not move drilling, could not find it anymore :-(");
			}
			backSide.addPlane2Drilling(drilling);
		}
		toMove.clear();
		for (Drilling drilling : frontSide.getPlane3Drillings()) {
			toMove.add(drilling);
		}
		for (Drilling drilling : toMove) {
			if (!frontSide.removePlane3Drilling(drilling)) {
				throw new RuntimeException("Could not move drilling, could not find it anymore :-(");
			}
			backSide.addPlane4Drilling(drilling);
		}
		toMove.clear();
		for (Drilling drilling : frontSide.getPlane4Drillings()) {
			toMove.add(drilling);
		}
		for (Drilling drilling : toMove) {
			if (!frontSide.removePlane4Drilling(drilling)) {
				throw new RuntimeException("Could not move drilling, could not find it anymore :-(");
			}
			backSide.addPlane3Drilling(drilling);
		}
		toMove.clear();

	}

	public String exportFrontSideTlf() {
		return frontSide.exportTlf();
	}

	public String exportBackSideTlf() {
		return backSide.exportTlf();
	}

	public void setThick(int thick) {
		this.dimensions.setThick(thick);
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMatId() {
		return matId;
	}

	public void setMatId(String matId) {
		this.matId = matId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getLength() {
		return dimensions.getLength();
	}

	public void setLength(double length) {
		this.dimensions.setLength(length);
	}

	public double getWidth() {
		return dimensions.getWidth();
	}

	public void setWidth(double width) {
		this.dimensions.setWidth(width);
	}

	public double getThick() {
		return dimensions.getThick();
	}

	public void setThick(double thick) {
		this.dimensions.setThick(thick);
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public PartDimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(PartDimensions dimensions) {
		this.dimensions = dimensions;
	}

	public CamPartSide getFrontSide() {
		return frontSide;
	}

	public CamPartSide getBackSide() {
		return backSide;
	}

	public void addDrilling(Drilling drilling) {
		IDrillingAdder frontSideAdder = drilling.getFrontSideAdder();
		if (angleMatch(drilling, 0, 0)) {
			frontSideAdder.addDrilling(frontSide, drilling);
		} else if (angleMatch(drilling, 180, 0) || angleMatch(drilling, -180, 0)) {
			backSide.addDrilling(drilling);
		} else if (angleMatch(drilling, -90, 0, 0) || angleMatch(drilling, 90, 0, 180) || angleMatch(drilling, 90, -90, 0)
				|| angleMatch(drilling, -90, -90, 0) || angleMatch(drilling, -90, 90, 0)) {
			frontSideAdder.addPlane1Drilling(frontSide, drilling);
		} else if (angleMatch(drilling, -90, 0, 180)) {
			frontSideAdder.addPlane2Drilling(frontSide, drilling);
		} else if (angleMatch(drilling, -90, 0, -90) || angleMatch(drilling, 90, 0, 90) || angleMatch(drilling, 0, 90, 0)) {
			frontSideAdder.addPlane3Drilling(frontSide, drilling);
		} else if (angleMatch(drilling, -90, 0, 90) || angleMatch(drilling, 0, -90, 0) || angleMatch(drilling, 180, 90, 0)) {
			frontSideAdder.addPlane4Drilling(frontSide, drilling);
		} else {
			throw new DrillingAngleException(drilling.getAngleX(), drilling.getAngleY(), drilling.getAngleZ());
		}
	}

	private boolean angleMatch(Drilling d, double ax, double ay) {
		return d.getAngleX() == ax && d.getAngleY() == ay;
	}

	private boolean angleMatch(Drilling d, double ax, double ay, double az) {
		return d.getAngleX() == ax && d.getAngleY() == ay && d.getAngleZ() == az;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("CamPart { ");
		sb.append("id=").append(id).append(", ");
		sb.append("orderId=").append(orderId).append(", ");
		sb.append("name=").append(name).append(", ");
		sb.append("matId=").append(matId).append(", ");
		sb.append("barcode=").append(barcode).append(", ");
		sb.append("length=").append(dimensions.getLength()).append(", ");
		sb.append("width=").append(dimensions.getWidth()).append(", ");
		sb.append("thick=").append(dimensions.getThick()).append(", ");
		sb.append("frontSide={ ").append(frontSide).append(" }");
		sb.append(" }");
		return sb.toString();
	}

	public void optimizeSides() {
		moveThroughDrillingsToFrontside();
		if (!backSide.isEmpty() && frontSide.hasOnlyHorizontalDrillings()) {
			moveHorizontalToBackside();
		}

	}

}
