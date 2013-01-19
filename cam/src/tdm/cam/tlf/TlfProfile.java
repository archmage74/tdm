package tdm.cam.tlf;

import java.util.HashMap;
import java.util.Map;

import tdm.cam.math.Dimensions;
import tdm.cam.model.imos.ProfileType;
import tdm.cam.tlf.transformer.IPlaneCoordinatesTransformer;

public class TlfProfile implements ITlfNode, ITlfEngineHolder {

	public static String entity = "Line.entity.jmte";

	// index within the campart plane for tlf reference between entity and work
	private int index;

	private ProfileType profileType;

	private double thick;
	
	private double planeX1;
	private double planeY1;

	private double planeX2;
	private double planeY2;

	protected IPlaneCoordinatesTransformer planeCoordinatesTransformer;

	@Override
	public String exportEntity() {
		StringBuffer tlf = new StringBuffer();

		Map<String, Object> profileModel = new HashMap<String, Object>();
		profileModel.put("profile", this);
		tlf.append(ENGINE.transform(entity, profileModel));

		return tlf.toString();
	}

	@Override
	public String exportWork() {
		return "";
	}

	@Override
	public boolean isSideIndependent() {
		return true;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public void setIndex(int index) {
		this.index = index;
	}

	public ProfileType getProfileType() {
		return profileType;
	}

	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}

	public double getThick() {
		return thick;
	}

	public void setThick(double thick) {
		this.thick = thick;
	}

	public double getPlaneX1() {
		return planeX1;
	}

	public double getPlaneY1() {
		return planeY1;
	}

	public double getPlaneX2() {
		return planeX2;
	}

	public double getPlaneY2() {
		return planeY2;
	}

	public void setPlaneCoordinatesTransformer(IPlaneCoordinatesTransformer planeCoordinatesTransformer) {
		this.planeCoordinatesTransformer = planeCoordinatesTransformer;
	}

	@Override
	public void calculatePlaneCoordinates(Dimensions dimensions) {
		switch (profileType) {
		case POS_V:
			planeX1 = planeCoordinatesTransformer.getPlaneX(dimensions, 0, thick, 0);
			planeY1 = planeCoordinatesTransformer.getPlaneY(dimensions, 0, thick, 0);
			planeX2 = planeCoordinatesTransformer.getPlaneX(dimensions, dimensions.getLength(), thick, 0);
			planeY2 = planeCoordinatesTransformer.getPlaneY(dimensions, dimensions.getLength(), thick, 0);
			break;
		case POS_H:
			planeX1 = planeCoordinatesTransformer.getPlaneX(dimensions, 0, dimensions.getWidth() - thick, 0);
			planeY1 = planeCoordinatesTransformer.getPlaneY(dimensions, 0, dimensions.getWidth() - thick, 0);
			planeX2 = planeCoordinatesTransformer.getPlaneX(dimensions, dimensions.getLength(), dimensions.getWidth() - thick, 0);
			planeY2 = planeCoordinatesTransformer.getPlaneY(dimensions, dimensions.getLength(), dimensions.getWidth() - thick, 0);
			break;
		case POS_L:
			planeX1 = planeCoordinatesTransformer.getPlaneX(dimensions, thick, 0, 0);
			planeY1 = planeCoordinatesTransformer.getPlaneY(dimensions, thick, 0, 0);
			planeX2 = planeCoordinatesTransformer.getPlaneX(dimensions, thick, dimensions.getWidth(), 0);
			planeY2 = planeCoordinatesTransformer.getPlaneY(dimensions, thick, dimensions.getWidth(), 0);
			break;
		case POS_R:
			planeX1 = planeCoordinatesTransformer.getPlaneX(dimensions, dimensions.getLength() - thick, 0, 0);
			planeY1 = planeCoordinatesTransformer.getPlaneY(dimensions, dimensions.getLength() - thick, 0, 0);
			planeX2 = planeCoordinatesTransformer.getPlaneX(dimensions, dimensions.getLength() - thick, dimensions.getWidth(), 0);
			planeY2 = planeCoordinatesTransformer.getPlaneY(dimensions, dimensions.getLength() - thick, dimensions.getWidth(), 0);
			break;
		default:
			throw new RuntimeException("unknown profile encountered");
	}

}

	@Override
	public String toString() {
		return "TlfProfile [index=" + index + ", profileType=" + profileType + ", thick=" + thick + ", planeX1=" + planeX1 + ", planeY1=" + planeY1
				+ ", planeX2=" + planeX2 + ", planeY2=" + planeY2 + ", planeCoordinatesTransformer=" + planeCoordinatesTransformer + "]";
	}

}
