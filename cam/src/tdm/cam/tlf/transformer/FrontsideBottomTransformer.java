package tdm.cam.tlf.transformer;

import tdm.cam.model.math.Dimensions;

/**
 * bottom = Plane 2 
 * @author wp
 */
public class FrontsideBottomTransformer implements IPlaneCoordinatesTransformer {

	@Override
	public double getPlaneX(Dimensions dim, double x, double y, double z) {
		return x;
	}

	@Override
	public double getPlaneY(Dimensions dim, double x, double y, double z) {
		return z;
	}

}
