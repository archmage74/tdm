package tdm.cam.tlf.imos2tlf.filter;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.math.Vector3;

public class UpFailFilter implements DrillingFilter {

	protected Vector3 allowDirection = new Vector3(0, 0, 1);
	
	@Override
	public ImosDrilling filter(ImosDrilling drilling) {
		if (drilling == null) {
			return null;
		}
		if (allowDirection.equals(drilling.getDirection())) {
			return null;
		}
		return drilling;
	}

}
