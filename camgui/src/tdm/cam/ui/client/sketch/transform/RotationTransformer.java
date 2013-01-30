package tdm.cam.ui.client.sketch.transform;

import tdm.cam.model.math.Vector3;
import tdm.cam.model.math.VectorUtil;

public class RotationTransformer implements ICoordinateTransformer {

	protected double angle;
	
	public RotationTransformer(double angle) {
		this.angle = angle;
	}

	@Override
	public Vector3 t(Vector3 v) {
		return VectorUtil.rotateByZ(v, angle);
	}

}
