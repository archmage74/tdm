package tdm.cam.tlf.transformer;

import tdm.cam.tlf.PartDimensions;

public class FrontsideRightTransformer implements IPlaneCoordinatesTransformer {

	@Override
	public double getPlaneX(PartDimensions dim, double x, double y, double z) {
		return y;
	}

	@Override
	public double getPlaneY(PartDimensions dim, double x, double y, double z) {
		return z;
	}

}
