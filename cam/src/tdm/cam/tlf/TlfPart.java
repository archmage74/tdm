package tdm.cam.tlf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tdm.cam.model.math.Dimensions;
import tdm.cam.model.math.Plane;
import tdm.cam.model.math.PlaneHelper;

//
//              plane 1 
//                top 
//         |---------------| 
//         |               | 
// plane 3 |               | plane 4 
//    left |               | right 
//         |               | 
//         |---------------| 
//              plane 2 
//              bottom
// 
public class TlfPart implements ITlfEngineHolder {

	public static String header = "TlfDocument.header.jmte";
	public static String footer = "TlfDocument.footer.jmte";

	private String id;

	private String orderId;

	private String name;

	private String matId;

	private String barcode;

	private Dimensions dimensions = new Dimensions();

	private TlfPartSide frontSide = new TlfFrontSide(dimensions);

	private TlfPartSide backSide = new TlfBackSide(dimensions);

	public TlfPart() {

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
			if (drilling instanceof TlfDrilling) {
				if (drilling.isSideIndependent()) {
					toMove.add(drilling);
				}
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
		if (name != null) {
			this.name = new String(name);
		} else {
			this.name = null;
		}
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = new String(barcode);
	}

	public Dimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimensions dimensions) {
		this.dimensions.setLength(dimensions.getLength());
		this.dimensions.setWidth(dimensions.getWidth());
		this.dimensions.setThick(dimensions.getThick());
	}

	public TlfPartSide getFrontSide() {
		return frontSide;
	}

	public TlfPartSide getBackSide() {
		return backSide;
	}

	public void addDrilling(TlfDrilling drilling) {
		Plane plane = PlaneHelper.getInstance().getPlaneForDirection(drilling.getDirection());
		if (plane == Plane.FRONT) {
			frontSide.addNode(drilling);
		} else if (plane == Plane.BACK) {
			backSide.addNode(drilling);
		} else if (plane == Plane.TOP) {
			frontSide.addPlane1Drilling(drilling);
		} else if (plane == Plane.BOTTOM) {
			frontSide.addPlane2Drilling(drilling);
		} else if (plane == Plane.LEFT) {
			frontSide.addPlane3Drilling(drilling);
		} else if (plane == Plane.RIGHT) {
			frontSide.addPlane4Drilling(drilling);
		} else {
			// throw new DrillingAngleException(drilling.getAngleX(), drilling.getAngleY(), drilling.getAngleZ());
			System.out.println("Schraegbohrung gefunden, wird ignoriert");
		}
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
