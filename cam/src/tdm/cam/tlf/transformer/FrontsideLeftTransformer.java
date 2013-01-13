package tdm.cam.tlf.transformer;

import tdm.cam.tlf.PartDimensions;

public class FrontsideLeftTransformer implements IPlaneCoordinatesTransformer {

	@Override
	public double getPlaneX(PartDimensions dim, double x, double y, double z) {
		return dim.getWidth() - y;
	}

	@Override
	public double getPlaneY(PartDimensions dim, double x, double y, double z) {
		return z;
	}

}