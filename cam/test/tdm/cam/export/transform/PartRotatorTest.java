package tdm.cam.export.transform;

import org.junit.Assert;
import org.junit.Test;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.TestDrillingParameters;
import tdm.cam.model.math.Dimensions;

public class PartRotatorTest {

	@Test
	public void dimension90() {
		double angle = 90;
		ImosPart part = new ImosPart();
		Dimensions d = new Dimensions(500, 200, 19);
		part.setDimensions(d);

		new PartRotator().rotatePart(part, angle);

		Dimensions actual = part.getDimensions();
		Dimensions expected = new Dimensions(200, 500, 19);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void drilling90() {
		double angle = 90;
		ImosPart part = new ImosPart();
		Dimensions d = new Dimensions(500, 200, 19);
		part.setDimensions(d);
		part.addDrilling(new TestDrillingParameters().x(200).y(100).z(0).endX(0).endY(0).create());
		
		new PartRotator().rotatePart(part, angle);
		
		double expectedX = 100;
		double expectedY = 300;
		ImosDrilling actual = part.getDrillings().get(0);
		Assert.assertEquals(expectedX, actual.getX(), 0.00001);
		Assert.assertEquals(expectedY, actual.getY(), 0.00001);
	}

	@Test
	public void drilling90_2() {
		double angle = 90;
		ImosPart part = new ImosPart();
		Dimensions d = new Dimensions(714, 404, 19);
		part.setDimensions(d);
		part.addDrilling(new TestDrillingParameters().x(74).y(378).z(0).endX(0).endY(0).create());
		
		new PartRotator().rotatePart(part, angle);
		
		double expectedX = 378;
		double expectedY = 640;
		ImosDrilling actual = part.getDrillings().get(0);
		Assert.assertEquals(expectedX, actual.getX(), 0.00001);
		Assert.assertEquals(expectedY, actual.getY(), 0.00001);
	}

}
