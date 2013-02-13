package tdm.cam.tlf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tdm.cam.model.math.Dimensions;

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
// plane 1, 3, 4: z measured from top
// plane 2: z measured from bottom
public class TlfPart implements ITlfEngineHolder {

	public static String header = "TlfDocument.header.jmte";
	public static String footer = "TlfDocument.footer.jmte";

	private String id;

	private String orderId;

	private String name;

	private String matId;

	private String barcode;

	private Dimensions dimensions = new Dimensions();

	protected List<TlfPartSide> sides = new ArrayList<TlfPartSide>(2);
	
	public TlfPart() {

	}

	public List<TlfDocument> createTlfDocuments() {
		List<TlfDocument> docs = new ArrayList<TlfDocument>();
		for (TlfPartSide side : sides) {
			if (!side.isEmpty()) {
				String name = getBarcode() + side.getSideName();
				String tlf = exportSideTlf(side);
				docs.add(new TlfDocument(name, tlf));
			}
		}			
		return docs;
	}

	public String exportSideTlf(TlfPartSide side) {
		StringBuffer tlf = new StringBuffer();
		Map<String, Object> docModel = new HashMap<String, Object>();

		tlf.append(ENGINE.transform(header, docModel));
		tlf.append(side.exportTlf());
		tlf.append(ENGINE.transform(footer, docModel));

		return tlf.toString();
	}

	/*
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
*/
	public void addSide(TlfPartSide side) {
		sides.add(side);
	}
	
	public List<TlfPartSide> getSides() {
		return new ArrayList<TlfPartSide>(sides);
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
		sb.append("sides=[");
		for (TlfPartSide side : sides) {
			sb.append(side).append(", ");
		}
		sb.append("]");
		sb.append(" }");
		return sb.toString();
	}

//	public void optimizeSides() {
//		moveThroughDrillingsToFrontside();
//		if (!backSide.isEmpty() && frontSide.hasOnlyHorizontalDrillings()) {
//			moveHorizontalToBackside();
//		}
//
//	}

}
