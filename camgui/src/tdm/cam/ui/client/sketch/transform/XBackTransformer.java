package tdm.cam.ui.client.sketch.transform;

import tdm.cam.model.math.Dimensions;
import tdm.cam.model.math.Vector3;

public class XBackTransformer implements ICoordinateTransformer {

	protected Dimensions dimensions;

	public XBackTransformer(Dimensions dimensions) {
		this.dimensions = dimensions;
	}
	
	@Override
	public Vector3 t(Vector3 v) {
		return new Vector3(-v.getX(), v.getY(), v.getZ());
	}

}
