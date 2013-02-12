package tdm.cam.model.math;

public class RotationMatrixFactory {

	public Matrix3x3 createXRotationInDegrees(double angleXInDegrees) {
		double a = Math.toRadians(angleXInDegrees);
		return createXRotation(a);
	}
		
	public Matrix3x3 createXRotation(double a) {
		Vector3 v1 = new Vector3(1, 0, 0);
		Vector3 v2 = new Vector3(0, Math.cos(a), -Math.sin(a));
		Vector3 v3 = new Vector3(0, Math.sin(a), Math.cos(a));

		return new Matrix3x3(new Vector3[] { v1, v2, v3 });
	}

	public Matrix3x3 createYRotationInDegrees(double angleYInDegrees) {
		double a = Math.toRadians(angleYInDegrees);
		return createYRotation(a);
	}

	public Matrix3x3 createYRotation(double a) {
		Vector3 v1 = new Vector3(Math.cos(a), 0, Math.sin(a));
		Vector3 v2 = new Vector3(0, 1, 0);
		Vector3 v3 = new Vector3(-Math.sin(a), 0, Math.cos(a));

		return new Matrix3x3(new Vector3[] { v1, v2, v3 });
	}

	public Matrix3x3 createZRotationInDegrees(double angleZInDegrees) {
		double a = Math.toRadians(angleZInDegrees);
		return createZRotation(a);
	}
	
	public Matrix3x3 createZRotation(double a) {
		Vector3 v1 = new Vector3(Math.cos(a), -Math.sin(a), 0);
		Vector3 v2 = new Vector3(Math.sin(a), Math.cos(a), 0);
		Vector3 v3 = new Vector3(0, 0, 1);

		return new Matrix3x3(new Vector3[] { v1, v2, v3 });
	}
	
	public Matrix3x3 createXYZRotationInDegrees(double angleX, double angleY, double angleZ) {
		Matrix3x3 rot = createZRotationInDegrees(angleZ);
		rot = rot.multiply(createYRotationInDegrees(angleY));
		rot = rot.multiply(createXRotationInDegrees(angleX));
		return rot;
	}

}
