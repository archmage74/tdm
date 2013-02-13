package tdm.cam.tlf.imos2tlf.filter;

import tdm.cam.model.imos.ImosDrilling;

public interface DrillingFilter {
	
	/**
	 * Returns the given drilling if it is allowed to pass through the filter.
	 */
	ImosDrilling filter(ImosDrilling drilling);
	
}
