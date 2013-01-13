package tdm.cam.tlf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *              plane 2
 *                top
 *         |---------------| 
 *         |               |
 * plane 3 |               | plane 4
 *  left   |               |  right
 *         |               |
 *         |---------------| 
 *              plane 1
 *              bottom
 *
 */
public class CamPart implements ITlfEngineHolder {

	public static String header = "TlfDocument.header.jmte";
	public static String footer = "TlfDocument.footer.jmte";

	private String id;

	private String orderId;

	private String name;

	private String matId;

	private String barcode;

	private PartDimensions dimensions = new PartDimensions();

	private CamPartSide frontSide = new FrontPartSide(dimensions);

	private CamPartSide backSide = new BackPartSide(dimensions);

	private HashMap<Integer, PartProfile> profileMap = new HashMap<Integer, PartProfile>(4);

	public CamPart() {

	}

	public List<TlfDocument> createTlfDocuments() {
		List<TlfDocument> docs = new ArrayList<TlfDocument>();
		if (!getFrontSide().isEmpty()) {
			String name = getBarcode() + TlfDocument.FRONT_SIDE_SUFFIX;
			String tlf = exportFrontSideTlf();
			docs.add(new TlfDocument(name, tlf));
		}
		if (!getBackSide().isEmpty()) {
			String name = getBarcode() + TlfDocument.BACK_SIDE_SUFFIX;
			String tlf = exportBackSideTlf();
			docs.add(new TlfDocument(name, tlf));
		}
		return docs;
	}
	
	public String exportFrontSideTlf() {
		StringBuffer tlf = new StringBuffer();
		Map<String, Object> docModel = new HashMap<String, Object>();
		
		// FIXME: profiles for both sides + where to put index generation
		frontSide.setProfileMap(profileMap);
		int index = frontSide.getDrillings().size();
		for (PartProfile profile : profileMap.values()) {
			profile.setIndex(index++);
		}
		
		tlf.append(ENGINE.transform(header, docModel));
		tlf.append(frontSide.exportTlf());
		tlf.append(ENGINE.transform(footer, docModel));
		return tlf.toString();
	}

	public String exportBackSideTlf() {
		StringBuffer tlf = new StringBuffer();
		Map<String, Object> docModel = new HashMap<String, Object>();
		tlf.append(ENGINE.transform(header, docModel));
		tlf.append(backSide.exportTlf());
		tlf.append(ENGINE.transform(footer, docModel));
		return tlf.toString();
	}

	public void moveThroughDrillingsToFrontside() {
		// FIXME better rule: move all to one side (prefer inner side) if possible
		List<ITlfNode> toMove = new ArrayList<ITlfNode>();
		for (ITlfNode drilling : backSide.getDrillings()) {
			if (drilling.isSideIndependent()) {
				toMove.add(drilling);
			}
		}
		for (ITlfNode drilling : toMove) {
			if (!backSide.removeDrilling(drilling)) {
				throw new RuntimeException("Could not move drilling, could not find it anymore :-(");
			}
			frontSide.addNode(drilling);
		}
	}

	public void moveHorizontalToBackside() {
		List<ITlfNode> toMove = new ArrayList<ITlfNode>();
		toMove.clear();
		for (ITlfNode drilling : frontSide.getPlane1Drillings()) {
			toMove.add(drilling);
		}
		for (ITlfNode drilling : toMove) {
			if (!frontSide.removePlane1Drilling(drilling)) {
				throw new RuntimeException("Could not move drilling, could not find it anymore :-(");
			}
			backSide.addPlane1Drilling(drilling);
		}
		toMove.clear();
		for (ITlfNode drilling : frontSide.getPlane2Drillings()) {
			toMove.add(drilling);
		}
		for (ITlfNode drilling : toMove) {
			if (!frontSide.removePlane2Drilling(drilling)) {
				throw new RuntimeException("Could not move drilling, could not find it anymore :-(");
			}
			backSide.addPlane2Drilling(drilling);
		}
		toMove.clear();
		for (ITlfNode drilling : frontSide.getPlane3Drillings()) {
			toMove.add(drilling);
		}
		for (ITlfNode drilling : toMove) {
			if (!frontSide.removePlane3Drilling(drilling)) {
				throw new RuntimeException("Could not move drilling, could not find it anymore :-(");
			}
			backSide.addPlane4Drilling(drilling);
		}
		toMove.clear();
		for (ITlfNode drilling : frontSide.getPlane4Drillings()) {
			toMove.add(drilling);
		}
		for (ITlfNode drilling : toMove) {
			if (!frontSide.removePlane4Drilling(drilling)) {
				throw new RuntimeException("Could not move drilling, could not find it anymore :-(");
			}
			backSide.addPlane3Drilling(drilling);
		}
		toMove.clear();

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
		if (angleMatch(drilling, 0, 0)) {
			frontSide.addNode(drilling);
		} else if (angleMatch(drilling, 180, 0) || angleMatch(drilling, -180, 0)) {
			backSide.addNode(drilling);
		} else if (angleMatch(drilling, -90, 0, 0) || angleMatch(drilling, 90, 0, 180) || angleMatch(drilling, 90, -90, 0)
				|| angleMatch(drilling, -90, -90, 0) || angleMatch(drilling, -90, 90, 0)) {
			frontSide.addPlane1Drilling(drilling);
		} else if (angleMatch(drilling, -90, 0, 180)) {
			frontSide.addPlane2Drilling(drilling);
		} else if (angleMatch(drilling, -90, 0, -90) || angleMatch(drilling, 90, 0, 90) || angleMatch(drilling, 0, 90, 0)) {
			frontSide.addPlane3Drilling(drilling);
		} else if (angleMatch(drilling, -90, 0, 90) || angleMatch(drilling, 0, -90, 0) || angleMatch(drilling, 180, 90, 0)) {
			frontSide.addPlane4Drilling(drilling);
		} else {
			throw new DrillingAngleException(drilling.getAngleX(), drilling.getAngleY(), drilling.getAngleZ());
		}
	}
	
	public void addProfile(PartProfile profile) {
		profileMap.put(profile.getPrfNo(), profile);
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
