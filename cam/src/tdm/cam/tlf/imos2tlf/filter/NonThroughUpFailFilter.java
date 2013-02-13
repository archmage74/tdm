package tdm.cam.tlf.imos2tlf.filter;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.math.Epsilon;
import tdm.cam.model.math.Vector3;

public class NonThroughUpFailFilter implements DrillingFilter {

	protected Vector3 allowDirection = new Vector3(0, 0, 1);
	
	protected double thick;
	
	public NonThroughUpFailFilter(double thick) {
		this.thick = thick;
	}
	
	@Override
	public ImosDrilling filter(ImosDrilling drilling) {
		if (drilling == null) {
			return null;
		}
		if (allowDirection.equals(drilling.getDirection()) && !Epsilon.greaterOrEqual(drilling.getDeep(), thick)) {
			return null;
		}
		return drilling;
	}

}
