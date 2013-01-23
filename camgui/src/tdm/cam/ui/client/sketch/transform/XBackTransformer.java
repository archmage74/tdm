package tdm.cam.ui.client.sketch.transform;

import tdm.cam.model.math.Dimensions;

public class XBackTransformer implements ICoordinateTransformer {

	protected Dimensions dimensions;

	public XBackTransformer(Dimensions dimensions) {
		this.dimensions = dimensions;
	}
	
	@Override
	public double tx(double x) {
		return - x;
	}

	@Override
	public double ty(double y) {
		return y;
	}

}
