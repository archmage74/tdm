package tdm.cam.tlf;

import java.util.HashMap;
import java.util.Map;

import tdm.cam.tlf.transformer.IPlaneCoordinatesTransformer;
import tdm.cam.vector.Vector3;

public class Drilling implements ITlfEngineHolder, ITlfNode {

	public static final Vector3 VECTOR_FRONT_SIDE = new Vector3(0, 0, 1);
	public static final Vector3 VECTOR_BACK_SIDE = new Vector3(0, 0, -1);
	public static final Vector3 VECTOR_PLANE_TOP = new Vector3(0, -1, 0);
	public static final Vector3 VECTOR_PLANE_BOTTOM = new Vector3(0, 1, 0);
	public static final Vector3 VECTOR_PLANE_LEFT = new Vector3(1, 0, 0);
	public static final Vector3 VECTOR_PLANE_RIGHT = new Vector3(-1, 0, 0);

	public static final Map<TlfPlane, Vector3> planeVectors = new HashMap<TlfPlane, Vector3>();
	static {
		planeVectors.put(TlfPlane.FRONT, VECTOR_FRONT_SIDE);
		planeVectors.put(TlfPlane.BACK, VECTOR_BACK_SIDE);
		planeVectors.put(TlfPlane.TOP, VECTOR_PLANE_TOP);
		planeVectors.put(TlfPlane.BOTTOM, VECTOR_PLANE_BOTTOM);
		planeVectors.put(TlfPlane.LEFT, VECTOR_PLANE_LEFT);
		planeVectors.put(TlfPlane.RIGHT, VECTOR_PLANE_RIGHT);
	}
	
	public static final double THROUGH_ADD_ON = 4.0;

	public static String entity = "Drilling.entity.jmte";
	public static String work = "Drilling.work.jmte";

	protected int index;
	protected double x;
	protected double y;
	protected double z;
	protected double angleX;
	protected double angleY;
	protected double angleZ;
	protected double deep;

	/** resulting X coordinate on the masterwork plane */
	protected double planeX;
	/** resulting Y coordinate on the masterwork plane */
	protected double planeY;

	protected IPlaneCoordinatesTransformer planeCoordinatesTransformer;
	
	protected PartDimensions dimensions;

	protected double diameter;
	protected int paramVelentrata;
	protected int paramRallforo;
	protected int paramTipounta;

	// FIXME check if it makes sense to add dimensions in camPart.addDrilling()
	public Drilling(PartDimensions dimensions) {
		super();
		this.dimensions = dimensions;
	}

	public Drilling(TlfDrillingTemplate template, PartDimensions dimensions) {
		this(dimensions);
		this.setDiameter(template.getDiameter());
		this.setParamRallforo(template.getParamRallforo());
		this.setParamTipounta(template.getParamTipounta());
		this.setParamVelentrata(template.getParamVelentrata());
	}
	
	@Override
	public void calculatePlaneCoordinates(PartDimensions dimensions) {
		planeX = planeCoordinatesTransformer.getPlaneX(dimensions, x, y, z);
		planeY = planeCoordinatesTransformer.getPlaneY(dimensions, x, y, z);
	};
	
	@Override
	public String exportEntity() {
		StringBuffer tlf = new StringBuffer();

		Map<String, Object> drillingModel = new HashMap<String, Object>();
		drillingModel.put("drilling", this);
		tlf.append(ENGINE.transform(entity, drillingModel));

		return tlf.toString();
	}

	@Override
	public String exportWork() {
		StringBuffer tlf = new StringBuffer();

		Map<String, Object> drillingModel = new HashMap<String, Object>();
		drillingModel.put("drilling", this);
		tlf.append(ENGINE.transform(work, drillingModel));

		return tlf.toString();
	}

	public TlfPlane getPlane() {
		Vector3 drillDirection = new Vector3(0, 0, 1);
		drillDirection.rotateXDegrees(getAngleX()).rotateYDegrees(getAngleY()).rotateZDegrees(getAngleZ());

		for (Map.Entry<TlfPlane, Vector3> planeVector : planeVectors.entrySet()) {
			if (drillDirection.equals(planeVector.getValue())) {
				return planeVector.getKey();
			}
		}
		
		return TlfPlane.DIAGONAL;
	}
	
	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public void setIndex(int index) {
		this.index = index;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
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

	public double getDeep() {
		return deep;
	}

	public void setDeep(double deep) {
		this.deep = deep;
		if (this.deep < 0) {
			this.deep *= -1;
		}
	}

	public double getMachineDeep() {
		if (isSideIndependent()) {
			return this.deep + Drilling.THROUGH_ADD_ON;
		} else {
			return this.deep;
		}
	}

	public boolean isHorizontal() {
		TlfPlane plane = getPlane();
		if (plane == TlfPlane.FRONT || plane == TlfPlane.BACK || plane == TlfPlane.DIAGONAL) {
			return false;
		} else {
			return true;
		}
	}

	public double getPlaneX() {
		return planeX;
	}

	public void setPlaneX(double planeX) {
		this.planeX = planeX;
	}

	public double getPlaneY() {
		return planeY;
	}

	public void setPlaneY(double planeY) {
		this.planeY = planeY;
	}

	@Override
	public void setPlaneCoordinatesTransformer(IPlaneCoordinatesTransformer planeCoordinatesTransformer) {
		this.planeCoordinatesTransformer = planeCoordinatesTransformer;
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public double getRadius() {
		return this.diameter / 2.0;
	}

	public int getParamVelentrata() {
		return paramVelentrata;
	}

	public void setParamVelentrata(int paramVelentrata) {
		this.paramVelentrata = paramVelentrata;
	}

	public int getParamRallforo() {
		return paramRallforo;
	}

	public void setParamRallforo(int paramRallforo) {
		this.paramRallforo = paramRallforo;
	}

	public int getParamTipounta() {
		return paramTipounta;
	}

	public void setParamTipounta(int paramTipounta) {
		this.paramTipounta = paramTipounta;
	}

	public PartDimensions getDimensions() {
		return dimensions;
	}

	@Override
	public boolean isSideIndependent() {
		if (isHorizontal()) {
			return false;
		}
		if (deep >= (dimensions.getThick() - 0.01)) { // 0.01mm epsilon to compare doubles
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Drilling { ");
		sb.append("index=").append(index).append(", ");
		sb.append("x=").append(x).append(", ");
		sb.append("y=").append(y).append(", ");
		sb.append("z=").append(z).append(", ");
		sb.append("angleX=").append(angleX).append(", ");
		sb.append("angleY=").append(angleY).append(", ");
		sb.append("angleZ=").append(angleZ).append(", ");
		sb.append("deep=").append(deep).append(", ");
		sb.append("diameter=").append(diameter).append(", ");
		sb.append("paramVelentrata=").append(paramVelentrata).append(", ");
		sb.append("paramRallforo=").append(paramRallforo).append(", ");
		sb.append("paramTipounta=").append(paramTipounta);
		sb.append(" }");
		return sb.toString();
	}

}