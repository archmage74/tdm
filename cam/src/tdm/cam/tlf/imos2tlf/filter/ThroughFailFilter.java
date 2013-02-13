package tdm.cam.tlf.imos2tlf.filter;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.math.Epsilon;
import tdm.cam.model.math.Vector3;

public class ThroughFailFilter implements DrillingFilter {

	protected List<Vector3> directions;
	
	protected double thick;
	
	public ThroughFailFilter(double thick) {
		this.thick = thick;
		directions = new ArrayList<Vector3>(2);
		directions.add(new Vector3(0, 0, 1));
		directions.add(new Vector3(0, 0, -1));
	}
	
	@Override
	public ImosDrilling filter(ImosDrilling drilling) {
		if (drilling == null) {
			return null;
		}
		for (Vector3 direction : directions) {
			if (drilling.getDirection().equals(direction) && Epsilon.greaterOrEqual(drilling.getDeep(), thick)) {
				return null;
			}
		}
		return drilling;
	}

}
