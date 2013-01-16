package tdm.cam.tlf.transformer;

import tdm.cam.math.Dimensions;

public class FrontsideRightTransformer implements IPlaneCoordinatesTransformer {

	@Override
	public double getPlaneX(Dimensions dim, double x, double y, double z) {
		return y;
	}

	@Override
	public double getPlaneY(Dimensions dim, double x, double y, double z) {
		return z;
	}

}
