package tdm.cam.tlf.transformer;

import tdm.cam.math.Dimensions;

/**
 * bottom = Plane 2 
 * @author wp
 */
public class BacksideBottomTransformer implements IPlaneCoordinatesTransformer {

	@Override
	public double getPlaneX(Dimensions dim, double x, double y, double z) {
		return dim.getLength() - x;
	}

	@Override
	public double getPlaneY(Dimensions dim, double x, double y, double z) {
		return z;
	}

}
