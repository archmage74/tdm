package tdm.cam.ui.client.sketch;

import tdm.cam.model.math.Dimensions;

public interface ICoordinateTransformer {
	
	/** transforms x coordinate */
	public double tx(double x, Dimensions d);
	
	/** transforms y coordinate */
	public double ty(double y, Dimensions d);
	
}