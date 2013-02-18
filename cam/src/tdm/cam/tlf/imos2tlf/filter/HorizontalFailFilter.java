package tdm.cam.tlf.imos2tlf.filter;

import tdm.cam.model.math.Vector3;

public class HorizontalFailFilter extends InDirectionListFailFilter {

	@Override
	protected void fillFailDirectionList() {
		failDirections.add(new Vector3(1, 0, 0));
		failDirections.add(new Vector3(0, 1, 0));
		failDirections.add(new Vector3(-1, 0, 0));
		failDirections.add(new Vector3(0, -1, 0));
	}
	
}
