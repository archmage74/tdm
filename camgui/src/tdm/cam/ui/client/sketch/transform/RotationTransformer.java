package tdm.cam.ui.client.sketch.transform;

import tdm.cam.model.math.Matrix3x3;
import tdm.cam.model.math.RotationMatrixFactory;
import tdm.cam.model.math.Vector3;

public class RotationTransformer implements ICoordinateTransformer {

	Matrix3x3 rot;
	
	public RotationTransformer(double angle) {
		RotationMatrixFactory rotationMatrixFactory = new RotationMatrixFactory();
		this.rot = rotationMatrixFactory.createZRotation(angle);
	}

	@Override
	public Vector3 t(Vector3 v) {
		return rot.multiply(v);
	}

}
