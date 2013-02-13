package tdm.cam.tlf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tdm.cam.model.math.Dimensions;
import tdm.cam.tlf.transformer.IPlaneCoordinatesTransformer;

public abstract class TlfPartSide implements ITlfEngineHolder {

	public static String pargen = "CamPartSide.pargen.jmte";
	public static String entities = "CamPartSide.entities.jmte";
	public static String works = "CamPartSide.works.jmte";

	protected Dimensions dimensions;
	protected String sideName;

	protected List<ITlfNode> drillings = new ArrayList<ITlfNode>();
	protected List<ITlfNode> plane1Drillings = new ArrayList<ITlfNode>();
	protected List<ITlfNode> plane2Drillings = new ArrayList<ITlfNode>();
	protected List<ITlfNode> plane3Drillings = new ArrayList<ITlfNode>();
	protected List<ITlfNode> plane4Drillings = new ArrayList<ITlfNode>();

	public TlfPartSide(String sideName, Dimensions dimensions) {
		this.dimensions = new Dimensions(dimensions);
		this.sideName = new String(sideName);
	}

	protected abstract IPlaneCoordinatesTransformer getUpTransformer();

	protected abstract IPlaneCoordinatesTransformer getTopTransformer();

	protected abstract IPlaneCoordinatesTransformer getBottomTransformer();

	protected abstract IPlaneCoordinatesTransformer getLeftTransformer();

	protected abstract IPlaneCoordinatesTransformer getRightTransformer();


	public void addNode(ITlfNode node) {
		node.setIndex(drillings.size());
		node.setPlaneCoordinatesTransformer(getUpTransformer());
		drillings.add(node);
	}

	public boolean removeDrilling(ITlfNode node) {
		return drillings.remove(node);
	}

	public void addPlane1Drilling(ITlfNode node) {
		node.setIndex(plane1Drillings.size());
		node.setPlaneCoordinatesTransformer(getTopTransformer());
		plane1Drillings.add(node);
	}

	public boolean removePlane1Drilling(ITlfNode node) {
		return plane1Drillings.remove(node);
	}

	public void addPlane2Drilling(ITlfNode node) {
		node.setIndex(plane2Drillings.size());
		node.setPlaneCoordinatesTransformer(getBottomTransformer());
		plane2Drillings.add(node);
	}

	public boolean removePlane2Drilling(ITlfNode node) {
		return plane2Drillings.remove(node);
	}

	public void addPlane3Drilling(ITlfNode node) {
		node.setIndex(plane3Drillings.size());
		node.setPlaneCoordinatesTransformer(getLeftTransformer());
		plane3Drillings.add(node);
	}

	public boolean removePlane3Drilling(ITlfNode node) {
		return plane3Drillings.remove(node);
	}

	public void addPlane4Drilling(ITlfNode node) {
		node.setIndex(plane4Drillings.size());
		node.setPlaneCoordinatesTransformer(getRightTransformer());
		plane4Drillings.add(node);
	}

	public boolean removePlane4Drilling(ITlfNode drilling) {
		return plane4Drillings.remove(drilling);
	}

	public String exportTlf() {
		StringBuffer tlf = new StringBuffer();
		
		tlf.append(createPargenTlf());
		tlf.append(createEntitiesTlf());
		tlf.append(createWorksTlf());

		return tlf.toString();
	}

	private String createPargenTlf() {
		Map<String, Object> camPartModel = new HashMap<String, Object>();
		camPartModel.put("camPartSide", this);
		String pargenTlf = ENGINE.transform(pargen, camPartModel);
		return pargenTlf;
	}

	private String createEntitiesTlf() {
		Map<String, Object> entitiesModel = new HashMap<String, Object>();
		String p0 = createPlaneEntities(drillings);
		
		entitiesModel.put("entitiesPlane0", p0);
		entitiesModel.put("entitiesPlane1", createPlaneEntities(plane1Drillings));
		entitiesModel.put("entitiesPlane2", createPlaneEntities(plane2Drillings));
		entitiesModel.put("entitiesPlane3", createPlaneEntities(plane3Drillings));
		entitiesModel.put("entitiesPlane4", createPlaneEntities(plane4Drillings));
		String entitiesTlf = ENGINE.transform(entities, entitiesModel);
		return entitiesTlf;
	}

	private String createPlaneEntities(List<ITlfNode> drillings) {
		StringBuffer drillingsBuffer = new StringBuffer();
		for (ITlfNode node : drillings) {
			node.calculatePlaneCoordinates(dimensions);
			drillingsBuffer.append(node.exportEntity());
		}
		return drillingsBuffer.toString();
	}

	private String createWorksTlf() {
		Map<String, Object> worksModel = new HashMap<String, Object>();
		worksModel.put("drillingsPlane0", createPlaneWorks(drillings));
		worksModel.put("drillingsPlane1", createPlaneWorks(plane1Drillings));
		worksModel.put("drillingsPlane2", createPlaneWorks(plane2Drillings));
		worksModel.put("drillingsPlane3", createPlaneWorks(plane3Drillings));
		worksModel.put("drillingsPlane4", createPlaneWorks(plane4Drillings));
		String worksTlf = ENGINE.transform(works, worksModel);
		return worksTlf;
	}

	private String createPlaneWorks(List<ITlfNode> drillings) {
		StringBuffer drillingsBuffer = new StringBuffer();
		for (ITlfNode drilling : drillings) {
			drillingsBuffer.append(drilling.exportWork());
		}
		return drillingsBuffer.toString();
	}

	public boolean isEmpty() {
		boolean noDrillings = true;
		for (ITlfNode d : drillings) {
			if (d instanceof TlfDrilling) {
				noDrillings = false;
				break;
			}
		}
		return noDrillings && plane1Drillings.isEmpty() && plane2Drillings.isEmpty() && plane3Drillings.isEmpty()
				&& plane4Drillings.isEmpty();
	}

	public boolean hasOnlyHorizontalDrillings() {
		boolean noDrillings = true;
		for (ITlfNode d : drillings) {
			if (d instanceof TlfDrilling) {
				noDrillings = false;
				break;
			}
		}
		if (!noDrillings) {
			return false;
		}
		if (plane1Drillings.isEmpty() && plane2Drillings.isEmpty() && plane3Drillings.isEmpty() && plane4Drillings.isEmpty()) {
			return false;
		}
		return true;
	}

	public double mirrorX(double x) {
		return dimensions.getLength() - x;
	}

	public double mirrorY(double y) {
		return dimensions.getWidth() - y;
	}

	public double mirrorZ(double z) {
		return dimensions.getThick() - z;
	}

	public List<ITlfNode> getDrillings() {
		return drillings;
	}

	public List<ITlfNode> getPlane1Drillings() {
		return plane1Drillings;
	}

	public List<ITlfNode> getPlane2Drillings() {
		return plane2Drillings;
	}

	public List<ITlfNode> getPlane3Drillings() {
		return plane3Drillings;
	}

	public List<ITlfNode> getPlane4Drillings() {
		return plane4Drillings;
	}

	public Dimensions getDimensions() {
		return dimensions;
	}

	public String getSideName() {
		return sideName;
	}

	public void setSideName(String sideName) {
		this.sideName = sideName;
	}

	@Override
	public String toString() {
		return "TlfPartSide [dimensions=" + dimensions + ", sideName=" + sideName + ", drillings=" + drillings + ", plane1Drillings="
				+ plane1Drillings + ", plane2Drillings=" + plane2Drillings + ", plane3Drillings=" + plane3Drillings + ", plane4Drillings="
				+ plane4Drillings + "]";
	}

}
