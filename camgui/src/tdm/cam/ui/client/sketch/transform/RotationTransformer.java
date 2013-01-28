package tdm.cam.ui.client.sketch.transform;

import tdm.cam.model.math.Vector3;

public class RotationTransformer implements ICoordinateTransformer {

	protected double angle;
	
	public RotationTransformer(double angle) {
		this.angle = angle;
	}

	@Override
	public Vector3 t(Vector3 v) {
		double x = v.getX() * Math.cos(angle) + v.getY() * Math.sin(angle);
		double y = v.getY() * Math.cos(angle) - v.getX() * Math.sin(angle);
		
		Vector3 t = new Vector3(x, y, v.getZ()); 
		return t;
	}

}
