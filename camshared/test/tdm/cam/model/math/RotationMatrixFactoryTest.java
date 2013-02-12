package tdm.cam.model.math;

import junit.framework.Assert;

import org.junit.Test;

public class RotationMatrixFactoryTest {

	private RotationMatrixFactory rotationMatrixFactory = new RotationMatrixFactory();

	// !!! angles in database are in opposite direction as rotation by matrix
	
	@Test
	public void plane1Test() {
		Matrix3x3 rot = rotationMatrixFactory.createXYZRotationInDegrees(-90, 0, 0);
		
		Vector3 expected = new Vector3(0, 1, 0);
		Vector3 actual = rot.multiply(Vector3.Z_UNIT_VECTOR);
		
		Assert.assertEquals(expected, actual);
		
	}

	@Test
	public void plane2Test() {
		Matrix3x3 rot = rotationMatrixFactory.createXYZRotationInDegrees(-90, 0, 180);
		
		Vector3 expected = new Vector3(0, -1, 0);
		Vector3 actual = rot.multiply(Vector3.Z_UNIT_VECTOR);
		
		Assert.assertEquals(expected, actual);
		
	}

	@Test
	public void rotateByZ0Test() {
		double angle = 0;
		Matrix3x3 rot = rotationMatrixFactory.createZRotationInDegrees(angle);
		Vector3 v = new Vector3(1, 2, 3);
		Vector3 expected = new Vector3(1, 2, 3);
		Vector3 actual = rot.multiply(v);
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateByZ270Test() {
		double angle = 270;
		Matrix3x3 rot = rotationMatrixFactory.createZRotationInDegrees(angle);
		Vector3 v = new Vector3(1, 2, 3);
		Vector3 expected = new Vector3(2, -1, 3);
		Vector3 actual = rot.multiply(v);
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateByZ180Test() {
		double angle = 180;
		Matrix3x3 rot = rotationMatrixFactory.createZRotationInDegrees(angle);
		Vector3 v = new Vector3(1, 2, 3);
		Vector3 expected = new Vector3(-1, -2, 3);
		Vector3 actual = rot.multiply(v);
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateByZ90Test() {
		double angle = 90;
		Matrix3x3 rot = rotationMatrixFactory.createZRotationInDegrees(angle);
		Vector3 v = new Vector3(1, 2, 3);
		Vector3 expected = new Vector3(-2, 1, 3);
		Vector3 actual = rot.multiply(v);
		
		Assert.assertEquals(expected, actual);
	}

}
