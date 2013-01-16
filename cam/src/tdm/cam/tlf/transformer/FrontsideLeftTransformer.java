package tdm.cam.tlf.transformer;

import tdm.cam.math.Dimensions;

public class FrontsideLeftTransformer implements IPlaneCoordinatesTransformer {

	@Override
	public double getPlaneX(Dimensions dim, double x, double y, double z) {
		return dim.getWidth() - y;
	}

	@Override
	public double getPlaneY(Dimensions dim, double x, double y, double z) {
		return z;
	}

}
