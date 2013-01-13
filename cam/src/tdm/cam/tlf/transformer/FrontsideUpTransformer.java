package tdm.cam.tlf.transformer;

import tdm.cam.tlf.PartDimensions;

public class FrontsideUpTransformer implements IPlaneCoordinatesTransformer {

	@Override
	public double getPlaneX(PartDimensions dim, double x, double y, double z) {
		return x;
	}

	@Override
	public double getPlaneY(PartDimensions dim, double x, double y, double z) {
		return dim.getWidth() - y;
	}

}