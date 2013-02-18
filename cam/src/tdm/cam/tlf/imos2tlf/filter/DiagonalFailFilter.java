package tdm.cam.tlf.imos2tlf.filter;

import tdm.cam.model.math.Vector3;

public class DiagonalFailFilter extends NotInDirectionListFailFilter {

	protected void fillFailDirectionList() {
		allowDirections.add(new Vector3(1, 0, 0));
		allowDirections.add(new Vector3(0, 1, 0));
		allowDirections.add(new Vector3(-1, 0, 0));
		allowDirections.add(new Vector3(0, -1, 0));
		allowDirections.add(new Vector3(0, 0, 1));
		allowDirections.add(new Vector3(0, 0, -1));
	}
	
}
