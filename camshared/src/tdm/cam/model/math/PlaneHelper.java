package tdm.cam.model.math;

import java.util.HashMap;
import java.util.Map;

import tdm.cam.model.imos.ImosDrilling;

public class PlaneHelper {

	public static final Vector3 VECTOR_FRONT_SIDE = new Vector3(0, 0, 1);
	public static final Vector3 VECTOR_BACK_SIDE = new Vector3(0, 0, -1);
	public static final Vector3 VECTOR_PLANE_TOP = new Vector3(0, -1, 0);
	public static final Vector3 VECTOR_PLANE_BOTTOM = new Vector3(0, 1, 0);
	public static final Vector3 VECTOR_PLANE_LEFT = new Vector3(1, 0, 0);
	public static final Vector3 VECTOR_PLANE_RIGHT = new Vector3(-1, 0, 0);

	protected static PlaneHelper planeHelper = new PlaneHelper();
	
	protected Map<Vector3, Plane> planeVectors = new HashMap<Vector3, Plane>();

	protected PlaneHelper() {
		planeVectors.put(VECTOR_FRONT_SIDE, Plane.FRONT);
		planeVectors.put(VECTOR_BACK_SIDE, Plane.BACK);
		planeVectors.put(VECTOR_PLANE_TOP, Plane.TOP);
		planeVectors.put(VECTOR_PLANE_BOTTOM, Plane.BOTTOM);
		planeVectors.put(VECTOR_PLANE_LEFT, Plane.LEFT);
		planeVectors.put(VECTOR_PLANE_RIGHT, Plane.RIGHT);
	}
	
	public static PlaneHelper getInstance() {
		return planeHelper;
	}
	
	public Plane getPlaneForRotations(double angleX, double angleY, double angleZ) {
		Vector3 drillDirection = new Vector3(0, 0, 1);
		drillDirection.rotateXDegrees(angleX).rotateYDegrees(angleZ).rotateZDegrees(angleZ);
		return getPlaneForDirection(drillDirection);
	}

	public Plane getPlaneForDirection(Vector3 direction) {
		for (Map.Entry<Vector3, Plane> planeVector : planeVectors.entrySet()) {
			if (planeVector.getKey().equals(direction)) {
				return planeVector.getValue();
			}
		}
		return Plane.DIAGONAL;
	}
	
	public boolean isHorizontal(ImosDrilling drilling) {
		Plane plane = getPlaneForDirection(drilling.getDirection());
		if (plane == Plane.FRONT || plane == Plane.BACK || plane == Plane.DIAGONAL) {
			return false;
		} else {
			return true;
		}
	}

}
