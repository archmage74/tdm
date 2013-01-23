package tdm.cam.ui.client.sketch;

import tdm.cam.model.math.PlaneHelper;
import tdm.cam.model.math.Vector3;

public class FrontDrillingFilter extends SingleSideDrillingFilter {

	@Override
	public Vector3 getFilterVector() {
		return PlaneHelper.VECTOR_FRONT_SIDE;
	}
		
}
