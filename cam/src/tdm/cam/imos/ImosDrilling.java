package tdm.cam.imos;

import java.util.HashMap;
import java.util.Map;

import tdm.cam.math.Vector3;
import tdm.cam.tlf.TlfPlane;


public class ImosDrilling {

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
	
	protected double x;

	protected double y;
	
	protected double z;
	
	protected double endX;
	
	protected double endY;
	
	protected double angleX;
	
	protected double angleY;
	
	protected double angleZ;

	protected double diameter;

	protected double deep;
	
	protected int numDrillings;

	
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

	public double getEndX() {
		return endX;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(double endY) {
		this.endY = endY;
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

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public double getDeep() {
		return deep;
	}

	public void setDeep(double deep) {
		this.deep = deep;
	}

	public int getNumDrillings() {
		return numDrillings;
	}

	public void setNumDrillings(int numDrillings) {
		this.numDrillings = numDrillings;
	}

	@Override
	public String toString() {
		return "ImosDrilling [x=" + x + ", y=" + y + ", z=" + z + ", endX=" + endX + ", endY=" + endY + ", angleX=" + angleX + ", angleY=" + angleY
				+ ", angleZ=" + angleZ + ", diameter=" + diameter + ", deep=" + deep + ", numDrillings=" + numDrillings + "]";
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
	
	public boolean isHorizontal() {
		TlfPlane plane = getPlane();
		if (plane == TlfPlane.FRONT || plane == TlfPlane.BACK || plane == TlfPlane.DIAGONAL) {
			return false;
		} else {
			return true;
		}
	}

}
