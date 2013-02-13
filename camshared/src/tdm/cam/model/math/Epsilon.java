package tdm.cam.model.math;

public class Epsilon {

	public static final double EPSILON = 0.00001;
	
	public static boolean equals(double d1, double d2) {
		if (Math.abs(d1 - d2) < Epsilon.EPSILON) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean greaterOrEqual(double d1, double d2) {
		if (d1 - d2 > - EPSILON) {
			return true;
		} else {
			return false;
		}
	}

}
