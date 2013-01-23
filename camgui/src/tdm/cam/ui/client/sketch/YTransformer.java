package tdm.cam.ui.client.sketch;

import tdm.cam.model.math.Dimensions;

public class YTransformer implements ICoordinateTransformer {

	@Override
	public double tx(double x, Dimensions d) {
		return x;
	}
	
	@Override
	public double ty(double y, Dimensions d) {
		return y;
	}

}
