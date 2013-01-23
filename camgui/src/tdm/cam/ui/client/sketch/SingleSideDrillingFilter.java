package tdm.cam.ui.client.sketch;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.math.Vector3;

public abstract class SingleSideDrillingFilter implements IDrillingFilter {

	public abstract Vector3 getFilterVector(); 
	
	@Override
	public boolean isDisplayed(ImosDrilling drilling) {
		Vector3 drillDirection = new Vector3(0, 0, 1);
		drillDirection.rotateXDegrees(drilling.getAngleX()).rotateYDegrees(drilling.getAngleY()).rotateZDegrees(drilling.getAngleZ());

		if (drillDirection.equals(getFilterVector())) {
			return false;
		} else {
			return true;
		}
	}

}
