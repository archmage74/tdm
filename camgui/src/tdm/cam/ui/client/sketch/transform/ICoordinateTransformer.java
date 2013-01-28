package tdm.cam.ui.client.sketch.transform;

import tdm.cam.model.math.Vector3;

public interface ICoordinateTransformer {
	
	public Vector3 t(Vector3 x);
	
}