package tdm.cam.tlf.imos2tlf.filter;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.math.Vector3;

public abstract class InDirectionListFailFilter implements IDrillingFilter {

	protected List<Vector3> failDirections = new ArrayList<Vector3>();

	public InDirectionListFailFilter() {
		fillFailDirectionList();
	}
	
	protected abstract void fillFailDirectionList();

	@Override
	public ImosDrilling filter(ImosDrilling drilling) {
		if (drilling == null) {
			return null;
		}
		for (Vector3 direction : failDirections) {
			if (drilling.getDirection().equals(direction)) {
				return null;
			}
		}
		return drilling;
	}
	
}
