package tdm.cam.tlf.transformer;

import tdm.cam.model.math.Dimensions;

/**
 * top = Plane 1 
 * @author wp
 */
public class FrontsideTopTransformer implements IPlaneCoordinatesTransformer {

	@Override
	public double getPlaneX(Dimensions dim, double x, double y, double z) {
		return x;
	}

	@Override
	public double getPlaneY(Dimensions dim, double x, double y, double z) {
		return z;
	}

}
