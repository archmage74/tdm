package tdm.cam.ui.client.sketch;

import tdm.cam.model.math.PlaneHelper;
import tdm.cam.model.math.Vector3;

public class BackDrillingFilter extends SingleSideDrillingFilter {

	@Override
	public Vector3 getFilterVector() {
		return PlaneHelper.VECTOR_INVISIBLE_SIDE;
	}

}
