package tdm.cam.tlf.transformer;

import tdm.cam.tlf.PartDimensions;

public class BacksideTopTransformer implements IPlaneCoordinatesTransformer {

	@Override
	public double getPlaneX(PartDimensions dim, double x, double y, double z) {
		return dim.getLength() - x;
	}

	@Override
	public double getPlaneY(PartDimensions dim, double x, double y, double z) {
		// FIXME i think it should be z (no mirror)
		return dim.getThick() - z;
	}

}
