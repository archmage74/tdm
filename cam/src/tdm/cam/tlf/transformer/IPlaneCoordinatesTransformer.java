package tdm.cam.tlf.transformer;

import tdm.cam.math.Dimensions;

public interface IPlaneCoordinatesTransformer {

	public double getPlaneX(Dimensions dim, double x, double y, double z);
	
	public double getPlaneY(Dimensions dim, double x, double y, double z);
	
}
