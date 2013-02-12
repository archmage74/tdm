package tdm.cam.ui.client.sketch;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.math.PlaneHelper;
import tdm.cam.model.math.Vector3;

public class HorizontalDrillingFilter implements IDrillingFilter {

	@Override
	public boolean isDisplayed(ImosDrilling drilling) {
		Vector3 drillDirection = drilling.getDirection();

		if (drillDirection.equals(PlaneHelper.VECTOR_PLANE_TOP) || drillDirection.equals(PlaneHelper.VECTOR_PLANE_BOTTOM) ||
				drillDirection.equals(PlaneHelper.VECTOR_PLANE_LEFT) || drillDirection.equals(PlaneHelper.VECTOR_PLANE_RIGHT)) {
			return false;
		} else {
			return true;
		}
	}

}
