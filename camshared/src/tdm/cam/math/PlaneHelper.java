package tdm.cam.math;

import java.util.HashMap;
import java.util.Map;

public class PlaneHelper {

	public static final Vector3 VECTOR_FRONT_SIDE = new Vector3(0, 0, 1);
	public static final Vector3 VECTOR_BACK_SIDE = new Vector3(0, 0, -1);
	public static final Vector3 VECTOR_PLANE_TOP = new Vector3(0, -1, 0);
	public static final Vector3 VECTOR_PLANE_BOTTOM = new Vector3(0, 1, 0);
	public static final Vector3 VECTOR_PLANE_LEFT = new Vector3(1, 0, 0);
	public static final Vector3 VECTOR_PLANE_RIGHT = new Vector3(-1, 0, 0);

	protected static PlaneHelper planeHelper = new PlaneHelper();
	
	protected Map<Plane, Vector3> planeVectors = new HashMap<Plane, Vector3>();

	protected PlaneHelper() {
		planeVectors.put(Plane.FRONT, VECTOR_FRONT_SIDE);
		planeVectors.put(Plane.BACK, VECTOR_BACK_SIDE);
		planeVectors.put(Plane.TOP, VECTOR_PLANE_TOP);
		planeVectors.put(Plane.BOTTOM, VECTOR_PLANE_BOTTOM);
		planeVectors.put(Plane.LEFT, VECTOR_PLANE_LEFT);
		planeVectors.put(Plane.RIGHT, VECTOR_PLANE_RIGHT);
	}
	
	public static PlaneHelper getInstance() {
		return planeHelper;
	}
	
	public Plane getPlaneForDirection(IDirection direction) {
		Vector3 drillDirection = new Vector3(0, 0, 1);
		drillDirection.rotateXDegrees(direction.getAngleX()).rotateYDegrees(direction.getAngleY()).rotateZDegrees(direction.getAngleZ());

		for (Map.Entry<Plane, Vector3> planeVector : planeVectors.entrySet()) {
			if (drillDirection.equals(planeVector.getValue())) {
				return planeVector.getKey();
			}
		}
		
		return Plane.DIAGONAL;
	}
	
}
