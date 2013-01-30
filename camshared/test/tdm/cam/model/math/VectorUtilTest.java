package tdm.cam.model.math;

import junit.framework.Assert;

import org.junit.Test;

public class VectorUtilTest {

	@Test
	public void rotateByZ0Test() {
		double angle = 0;
		Vector3 v = new Vector3(1, 2, 3);
		Vector3 expected = new Vector3(1, 2, 3);
		Vector3 actual = VectorUtil.rotateByZInDegrees(v, angle);
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateByZ90Test() {
		double angle = 90;
		Vector3 v = new Vector3(1, 2, 3);
		Vector3 expected = new Vector3(2, -1, 3);
		Vector3 actual = VectorUtil.rotateByZInDegrees(v, angle);
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateByZ180Test() {
		double angle = 180;
		Vector3 v = new Vector3(1, 2, 3);
		Vector3 expected = new Vector3(-1, -2, 3);
		Vector3 actual = VectorUtil.rotateByZInDegrees(v, angle);
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateByZ270Test() {
		double angle = 270;
		Vector3 v = new Vector3(1, 2, 3);
		Vector3 expected = new Vector3(-2, 1, 3);
		Vector3 actual = VectorUtil.rotateByZInDegrees(v, angle);
		
		Assert.assertEquals(expected, actual);
	}

}
