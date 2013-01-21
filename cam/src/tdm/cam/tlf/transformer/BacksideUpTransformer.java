package tdm.cam.tlf.transformer;

import tdm.cam.model.math.Dimensions;

public class BacksideUpTransformer implements IPlaneCoordinatesTransformer {

	@Override
	public double getPlaneX(Dimensions dim, double x, double y, double z) {
		return dim.getLength() - x;
	}

	@Override
	public double getPlaneY(Dimensions dim, double x, double y, double z) {
		return dim.getWidth() - y;
	}

}
