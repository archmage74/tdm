package tdm.cam.tlf.transformer;

import tdm.cam.model.math.Dimensions;

/**
 * top = Plane 1 
 * @author wp
 */
public class BacksideTopTransformer implements IPlaneCoordinatesTransformer {

	@Override
	public double getPlaneX(Dimensions dim, double x, double y, double z) {
		return dim.getLength() - x;
	}

	@Override
	public double getPlaneY(Dimensions dim, double x, double y, double z) {
		return dim.getThick() - z;
	}

}
