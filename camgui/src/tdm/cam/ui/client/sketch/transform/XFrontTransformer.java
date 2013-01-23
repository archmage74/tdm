package tdm.cam.ui.client.sketch.transform;


public class XFrontTransformer implements ICoordinateTransformer {

	@Override
	public double tx(double x) {
		return x;
	}
	
	@Override
	public double ty(double y) {
		return y;
	}
	
}
