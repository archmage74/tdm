package tdm.cam.model.math;

import junit.framework.Assert;

import org.junit.Test;

public class Matrix3x3Test {

	@Test
	public void multiplyTest() {
		Vector3 mv1 = new Vector3(1, 2, 3);
		Vector3 mv2 = new Vector3(4, 5, 6);
		Vector3 mv3 = new Vector3(7, 8, 9);
		Matrix3x3 m = new Matrix3x3(mv1, mv2, mv3);
		
		Vector3 nv1 = new Vector3(3, 2, 1);
		Vector3 nv2 = new Vector3(6, 5, 4);
		Vector3 nv3 = new Vector3(9, 8, 7);
		Matrix3x3 n = new Matrix3x3(nv1, nv2, nv3);
		
		Matrix3x3 actual = m.multiply(n);
		
		Vector3 ev1 = new Vector3(42, 36, 30);
		Vector3 ev2 = new Vector3(96, 81, 66);
		Vector3 ev3 = new Vector3(150, 126, 102);
		Matrix3x3 expected = new Matrix3x3(ev1, ev2, ev3);
		
		Assert.assertEquals(expected, actual);
	}
	
}
