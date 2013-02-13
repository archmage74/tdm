package tdm.cam.tlf.imos2tlf.filter;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.math.Vector3;

public class HorizontalFailFilter implements DrillingFilter {

	protected List<Vector3> allowDirections;
	
	public HorizontalFailFilter() {
		allowDirections = new ArrayList<Vector3>(4);
		allowDirections.add(new Vector3(1, 0, 0));
		allowDirections.add(new Vector3(0, 1, 0));
		allowDirections.add(new Vector3(-1, 0, 0));
		allowDirections.add(new Vector3(0, -1, 0));
	}
	
	@Override
	public ImosDrilling filter(ImosDrilling drilling) {
		if (drilling == null) {
			return null;
		}
		for (Vector3 direction : allowDirections) {
			if (drilling.getDirection().equals(direction)) {
				return null;
			}
		}
		return drilling;
	}
	
}
