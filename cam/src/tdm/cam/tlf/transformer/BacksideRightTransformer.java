package tdm.cam.tlf.transformer;

import tdm.cam.model.math.Dimensions;

public class BacksideRightTransformer implements IPlaneCoordinatesTransformer {

	@Override
	public double getPlaneX(Dimensions dim, double x, double y, double z) {
		return y;
	}

	@Override
	public double getPlaneY(Dimensions dim, double x, double y, double z) {
		return dim.getThick() - z;
	}

}
