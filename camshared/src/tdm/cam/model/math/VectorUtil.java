package tdm.cam.model.math;

public class VectorUtil {

	public static Vector3 rotateByZInDegrees(Vector3 v, double angleInDegrees) {
		double angle = degreesToRadiant(angleInDegrees);
		return rotateByZ(v, angle);
	}

	
	public static Vector3 rotateByZ(Vector3 v, double angle) {
		double x = v.getX() * Math.cos(angle) + v.getY() * Math.sin(angle);
		double y = v.getY() * Math.cos(angle) - v.getX() * Math.sin(angle);
		
		Vector3 t = new Vector3(x, y, v.getZ()); 
		return t;
	}

	public static double degreesToRadiant(double angleInDegrees) {
		double angle = angleInDegrees / 180 * Math.PI;
		return angle;
	}

}
