package tdm.cam.ui.client.sketch.transform;

public interface ICoordinateTransformer {
	
	/** transforms x coordinate */
	public double tx(double x);
	
	/** transforms y coordinate */
	public double ty(double y);
	
}