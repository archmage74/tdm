package tdm.cam.tlf.transformer;

import tdm.cam.tlf.PartDimensions;

public interface IPlaneCoordinatesTransformer {

	public double getPlaneX(PartDimensions dim, double x, double y, double z);
	
	public double getPlaneY(PartDimensions dim, double x, double y, double z);
	
}
