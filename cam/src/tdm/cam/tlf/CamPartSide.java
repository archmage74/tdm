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

	public void addPlane2Drilling(Drilling drilling) {
		drilling.setIndex(plane2Drillings.size());
		plane2Drillings.add(drilling);
	}

	public void addPlane3Drilling(Drilling drilling) {
		drilling.setIndex(plane3Drillings.size());
		plane3Drillings.add(drilling);
	}

	public void addPlane4Drilling(Drilling drilling) {
		drilling.setIndex(plane4Drillings.size());
		plane4Drillings.add(drilling);
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

	public List<Drilling> getDrillings() {
		return drillings;
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
