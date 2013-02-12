package tdm.cam.ui.client.sketch;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.math.Vector3;

public abstract class SingleSideDrillingFilter implements IDrillingFilter {

	public abstract Vector3 getFilterVector(); 
	
	@Override
	public boolean isDisplayed(ImosDrilling drilling) {
		Vector3 drillDirection = drilling.getDirection();

		if (drillDirection.equals(getFilterVector())) {
			return false;
		} else {
			return true;
		}
	}

}
