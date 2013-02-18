package tdm.cam.tlf.imos2tlf.filter;

import tdm.cam.model.imos.ImosDrilling;

public interface IDrillingFilter {
	
	/**
	 * Returns the given drilling if it is allowed to pass through the filter.
	 */
	ImosDrilling filter(ImosDrilling drilling);
	
}
