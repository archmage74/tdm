package tdm.cam.tlf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CamPartSide implements ITlfNode {

	public static String pargen = "CamPartSide.pargen.jmte";
	public static String entities = "CamPartSide.entities.jmte";
	public static String works = "CamPartSide.works.jmte";

	protected PartDimensions dimensions;

	protected List<Drilling> drillings = new ArrayList<Drilling>();
	protected List<Drilling> plane1Drillings = new ArrayList<Drilling>();
	protected List<Drilling> plane2Drillings = new ArrayList<Drilling>();
	protected List<Drilling> plane3Drillings = new ArrayList<Drilling>();
	protected List<Drilling> plane4Drillings = new ArrayList<Drilling>();

	public CamPartSide(PartDimensions dimensions) {
		this.dimensions = dimensions;
	}

	public void addDrilling(Drilling drilling) {
		drilling.setIndex(drillings.size());
		drillings.add(drilling);
	}

	public boolean removeDrilling(Drilling drilling) {
		return drillings.remove(drilling);
	}

	public void addPlane1Drilling(Drilling drilling) {
		drilling.setIndex(plane1Drillings.size());
		plane1Drillings.add(drilling);
	}

	public boolean removePlane1Drilling(Drilling drilling) {
		return plane1Drillings.remove(drilling);
	}

	public void addPlane2Drilling(Drilling drilling) {
		drilling.setIndex(plane2Drillings.size());
		plane2Drillings.add(drilling);
	}

	public boolean removePlane2Drilling(Drilling drilling) {
		return plane2Drillings.remove(drilling);
	}

	public void addPlane3Drilling(Drilling drilling) {
		drilling.setIndex(plane3Drillings.size());
		plane3Drillings.add(drilling);
	}

	public boolean removePlane3Drilling(Drilling drilling) {
		return plane3Drillings.remove(drilling);
	}

	public void addPlane4Drilling(Drilling drilling) {
		drilling.setIndex(plane4Drillings.size());
		plane4Drillings.add(drilling);
	}

	public boolean removePlane4Drilling(Drilling drilling) {
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
		entitiesModel.put("entitiesPlane0", createPlaneEntities(drillings));
		entitiesModel.put("entitiesPlane1", createPlaneEntities(plane1Drillings));
		entitiesModel.put("entitiesPlane2", createPlaneEntities(plane2Drillings));
		entitiesModel.put("entitiesPlane3", createPlaneEntities(plane3Drillings));
		entitiesModel.put("entitiesPlane4", createPlaneEntities(plane4Drillings));
		String entitiesTlf = ENGINE.transform(entities, entitiesModel);
		return entitiesTlf;
	}

	private String createPlaneEntities(List<Drilling> drillings) {
		StringBuffer drillingsBuffer = new StringBuffer();
		for (Drilling drilling : drillings) {
			drillingsBuffer.append(drilling.exportEntity(dimensions));
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

	private String createPlaneWorks(List<Drilling> drillings) {
		StringBuffer drillingsBuffer = new StringBuffer();
		for (Drilling drilling : drillings) {
			drillingsBuffer.append(drilling.exportWork(dimensions));
		}
		return drillingsBuffer.toString();
	}

	public boolean isEmpty() {
		return drillings.isEmpty() && plane1Drillings.isEmpty() && plane2Drillings.isEmpty() && plane3Drillings.isEmpty()
				&& plane4Drillings.isEmpty();
	}

	public boolean hasOnlyHorizontalDrillings() {
		if (!drillings.isEmpty()) {
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

	public List<Drilling> getDrillings() {
		return drillings;
	}


	public List<Drilling> getPlane1Drillings() {
		return plane1Drillings;
	}

	public void setPlane1Drillings(List<Drilling> plane1Drillings) {
		this.plane1Drillings = plane1Drillings;
	}

	public List<Drilling> getPlane2Drillings() {
		return plane2Drillings;
	}

	public void setPlane2Drillings(List<Drilling> plane2Drillings) {
		this.plane2Drillings = plane2Drillings;
	}

	public List<Drilling> getPlane3Drillings() {
		return plane3Drillings;
	}

	public void setPlane3Drillings(List<Drilling> plane3Drillings) {
		this.plane3Drillings = plane3Drillings;
	}

	public List<Drilling> getPlane4Drillings() {
		return plane4Drillings;
	}

	public void setPlane4Drillings(List<Drilling> plane4Drillings) {
		this.plane4Drillings = plane4Drillings;
	}

	public void setDrillings(List<Drilling> drillings) {
		this.drillings = new ArrayList<Drilling>(drillings);
	}

	public PartDimensions getDimensions() {
		return dimensions;
	}

	public String toString() {
		return toString(this.getClass().getCanonicalName());
	}

	public String toString(String classname) {
		StringBuffer sb = new StringBuffer();
		sb.append(classname).append(" { ");
		sb.append("dimensions={ ");
		if (dimensions == null) {
			sb.append("null");
		} else {
			sb.append(dimensions.toString());
		}
		sb.append(" }");
		sb.append("drillings={ ");
		if (drillings == null) {
			sb.append("null");
		} else {
			for (Drilling drilling : drillings) {
				sb.append(drilling.toString()).append(", ");
			}
		}
		sb.append(" }");

		sb.append(" }");
		return sb.toString();
	}
}
