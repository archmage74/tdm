package tdm.cam.vector;

import junit.framework.Assert;

import org.junit.Test;

public class Vector3Test {

	@Test
	public void equalsXTest() {
		Vector3 v1 = new Vector3(1.23, 0, 0);
		Vector3 v2 = new Vector3(1.23000001, 0, 0);
		
		Assert.assertTrue(v1.equals(v2));
	}

	@Test
	public void notEqualsXTest() {
		Vector3 v1 = new Vector3(1.23, 0, 0);
		Vector3 v2 = new Vector3(1.24, 0, 0);
		
		Assert.assertFalse(v1.equals(v2));
	}

	@Test
	public void equalsYTest() {
		Vector3 v1 = new Vector3(0, 1.23, 0);
		Vector3 v2 = new Vector3(0, 1.23000001, 0);
		
		Assert.assertTrue(v1.equals(v2));
	}

	@Test
	public void notEqualsYTest() {
		Vector3 v1 = new Vector3(0, 1.23, 0);
		Vector3 v2 = new Vector3(0, 1.24, 0);
		
		Assert.assertFalse(v1.equals(v2));
	}

	@Test
	public void equalsZTest() {
		Vector3 v1 = new Vector3(0, 0, 1.23);
		Vector3 v2 = new Vector3(0, 0, 1.23000001);
		
		Assert.assertTrue(v1.equals(v2));
	}

	@Test
	public void notEqualsZTest() {
		Vector3 v1 = new Vector3(0, 0, 1.23);
		Vector3 v2 = new Vector3(0, 0, 1.24);
		
		Assert.assertFalse(v1.equals(v2));
	}

	@Test
	public void rotateV001ByX90Test() {
		Vector3 actual = new Vector3(0, 0, 1);
		actual.rotateXDegrees(90);
		
		Vector3 expected = new Vector3(0, 1, 0);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateV010ByX90Test() {
		Vector3 actual = new Vector3(0, 1, 0);
		actual.rotateXDegrees(90);
		
		Vector3 expected = new Vector3(0, 0, -1);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateV100ByX90Test() {
		Vector3 actual = new Vector3(1, 0, 0);
		actual.rotateXDegrees(90);
		
		Vector3 expected = new Vector3(1, 0, 0);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateV001ByY90Test() {
		Vector3 actual = new Vector3(0, 0, 1);
		actual.rotateYDegrees(90);
		
		Vector3 expected = new Vector3(1, 0, 0);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateV010ByY90Test() {
		Vector3 actual = new Vector3(0, 1, 0);
		actual.rotateYDegrees(90);
		
		Vector3 expected = new Vector3(0, 1, 0);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateV100ByY90Test() {
		Vector3 actual = new Vector3(1, 0, 0);
		actual.rotateYDegrees(90);
		
		Vector3 expected = new Vector3(0, 0, -1);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateV001ByZ90Test() {
		Vector3 actual = new Vector3(0, 0, 1);
		actual.rotateZDegrees(90);
		
		Vector3 expected = new Vector3(0, 0, 1);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateV010ByZ90Test() {
		Vector3 actual = new Vector3(0, 1, 0);
		actual.rotateZDegrees(90);
		
		Vector3 expected = new Vector3(1, 0, 0);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateV100ByZ90Test() {
		Vector3 actual = new Vector3(1, 0, 0);
		actual.rotateZDegrees(90);
		
		Vector3 expected = new Vector3(0, -1, 0);
		Assert.assertEquals(expected, actual);
	}

}
